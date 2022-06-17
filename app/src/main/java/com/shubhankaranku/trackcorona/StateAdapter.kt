package com.shubhankaranku.trackcorona


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StateAdapter (private val stateList:List<StateModel>) : RecyclerView.Adapter<StateAdapter.StateRvViewHolder>() {

    class StateRvViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val stateNameTv: TextView = itemView.findViewById(R.id.stateNameId)
        val activeCasesTv: TextView = itemView.findViewById(R.id.ActiveCasesId)
        val confirmedTv: TextView = itemView.findViewById(R.id.ConfirmedId)
        val recoveredTv: TextView = itemView.findViewById(R.id.recoveredId)
        val DeathTv: TextView = itemView.findViewById(R.id.DeathId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateRvViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.testing, parent, false)
        return StateRvViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StateRvViewHolder, position: Int) {
       val stateData = stateList[position]
        holder.stateNameTv.text = stateData.statecode
        holder.activeCasesTv.text = stateData.active.toString()
        holder.confirmedTv.text = stateData.confirmed.toString()
        holder.recoveredTv.text = stateData.recovered.toString()
        holder.DeathTv.text = stateData.deceased.toString()
    }

    override fun getItemCount(): Int {
        return stateList.size
    }


}