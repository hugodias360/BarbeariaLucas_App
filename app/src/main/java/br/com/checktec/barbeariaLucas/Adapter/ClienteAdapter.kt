package br.com.checktec.barbeariaLucas.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.checktec.barbeariaLucas.Models.Cliente
import br.com.checktec.barbeariaLucas.R

class ClienteAdapter (
        val clientes: List<Cliente>,
        val onClick: (Cliente) -> Unit): RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    // ViewHolder com os elemetos da tela
    class ClienteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardApelido : TextView
        val cardEmail : TextView
        val cardCelular : TextView
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNomeCliente)
            cardApelido = view.findViewById<TextView>(R.id.cardApelidoCliente)
            cardEmail = view.findViewById<TextView>(R.id.cardEmailCliente)
            cardCelular = view.findViewById<TextView>(R.id.cardCelularCliente)
            cardView = view.findViewById<CardView>(R.id.card_cliente)

        }

    }

    // Quantidade de servicos na lista

    override fun getItemCount() = this.clientes.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cliente, parent, false)

        // retornar ViewHolder
        val holder = ClienteViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto servico
        val cliente = clientes[position]

        // atualizar dados de servico

        holder.cardNome.text = cliente.nome
        holder.cardApelido.text = cliente.apelido
        holder.cardCelular.text = cliente.celular
        holder.cardEmail.text = cliente.email

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(cliente)}
    }
}