package br.com.checktec.barbeariaLucas.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.checktec.barbeariaLucas.Models.Servico
import br.com.checktec.barbeariaLucas.R

// define o construtor que recebe a lista de servicos e o evento de clique
class ServicoAdapter (
        val servicos: List<Servico>,
        val onClick: (Servico) -> Unit): RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    // ViewHolder com os elemetos da tela
    class ServicoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardValor : TextView
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNomeServico)
            cardValor = view.findViewById<TextView>(R.id.cardValorServico)
            cardView = view.findViewById<CardView>(R.id.card_servicos)

        }

    }

    // Quantidade de servicos na lista

    override fun getItemCount() = this.servicos.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_servico, parent, false)

        // retornar ViewHolder
        val holder = ServicoViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto servico
        val servico = servicos[position]

        // atualizar dados de servico

        holder.cardNome.text = servico.nome
        holder.cardValor.text= "R$ " + servico.valor

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(servico)}
    }
}