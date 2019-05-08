package br.com.checktec.barbeariaLucas.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.checktec.barbeariaLucas.Models.Cabeleleiro
import br.com.checktec.barbeariaLucas.R

class CabeleleiroAdapter (
    val cabeleleiros: List<Cabeleleiro>,
    val onClick: (Cabeleleiro) -> Unit): RecyclerView.Adapter<CabeleleiroAdapter.CabeleleiroViewHolder>() {

        // ViewHolder com os elemetos da tela
        class CabeleleiroViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val cardNome: TextView
            val cardEmail : TextView
            val cardCelular : TextView
            var cardView: CardView

            init {
                cardNome = view.findViewById<TextView>(R.id.cardNomeCabeleleiro)
                cardEmail = view.findViewById<TextView>(R.id.cardEmailCabeleleiro)
                cardCelular = view.findViewById<TextView>(R.id.cardCelularCabeleleiro)
                cardView = view.findViewById<CardView>(R.id.card_cabeleleiro)

            }

        }


        override fun getItemCount() = this.cabeleleiros.size

        // inflar layout do adapter

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabeleleiroViewHolder {
            // infla view no adapter
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cabeleleiro, parent, false)

            // retornar ViewHolder
            val holder = CabeleleiroViewHolder(view)
            return holder
        }

        // bind para atualizar Views com os dados

        override fun onBindViewHolder(holder: CabeleleiroViewHolder, position: Int) {
            val context = holder.itemView.context


            val cabeleleiro = cabeleleiros[position]


            holder.cardNome.text = cabeleleiro.nome
            holder.cardCelular.text = cabeleleiro.celular
            holder.cardEmail.text = cabeleleiro.email

            // adiciona evento de clique
            holder.itemView.setOnClickListener {onClick(cabeleleiro)}
        }
}