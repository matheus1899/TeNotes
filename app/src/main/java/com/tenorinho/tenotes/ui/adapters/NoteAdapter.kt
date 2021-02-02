package com.tenorinho.tenotes.ui.adapters

import android.app.Activity
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.databinding.ItemListNoteBinding
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.ui.INavigation

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>, View.OnCreateContextMenuListener{
    var array:List<Note> = ArrayList<Note>()
    var activity:Activity

    var pos:Int = 0
    constructor(act:Activity):super(){
        this.activity = act
    }

    class ViewHolder : RecyclerView.ViewHolder{
        var txtTitle:TextView
        var txtContent:TextView
        var root:ConstraintLayout
        constructor(view:View):super(view){
            root = view.findViewById(R.id.root_item_list_note)
            txtTitle = view.findViewById(R.id.title_item_list_note)
            txtContent = view.findViewById(R.id.annotation_item_list_note)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = ItemListNoteBinding.inflate(LayoutInflater.from(parent.context)).root
        view.setOnCreateContextMenuListener(this)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return array.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note:Note? = array[position]
        if(note != null){
            holder.root.setOnClickListener {
                if(activity is INavigation){
                    val listener:INavigation = activity as INavigation
                    listener.navigateToShowNoteFragment(note)
                }
            }
            holder.root.setOnLongClickListener {
                pos = position
                return@setOnLongClickListener false
            }
            holder.txtTitle.text = note.title
            with(note.content){
                if(this.isEmpty() || this.isBlank()){
                    holder.txtContent.visibility = View.GONE
                }
                else{
                    holder.txtContent.visibility = View.VISIBLE
                    holder.txtContent.text = this
                }
            }
            holder.txtContent.text = note.content
        }
    }
    fun setNotes(notes:List<Note>){
        this.array = notes
        notifyDataSetChanged()
    }
    override fun onCreateContextMenu(menu: ContextMenu?, view: View?, info: ContextMenu.ContextMenuInfo?){
        menu?.add(Menu.NONE, R.id.edit_note, Menu.NONE, "Edit note")
        menu?.add(Menu.NONE, R.id.delete_note, Menu.NONE, "Delete note")
    }
}