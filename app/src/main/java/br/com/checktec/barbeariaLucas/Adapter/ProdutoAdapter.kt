package br.com.checktec.barbeariaLucas.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import br.com.checktec.barbeariaLucas.Models.Produto
import br.com.checktec.barbeariaLucas.R


class ProdutoAdapter (
        val produtos: List<Produto>,
        val onClick: (Produto) -> Unit):
    RecyclerView.Adapter<ProdutoAdapter.ProdutosViewHolder>() {
    // ViewHolder com os elementos da tela
    class ProdutosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardValor : TextView
        var cardEspecificacao: TextView
        var cardView: CardView
        init {
            cardNome = view.findViewById<TextView>(R.id.cardnomeProduto)
            cardValor = view.findViewById<TextView>(R.id.cardValorProduto)
            cardEspecificacao = view.findViewById<TextView>(R.id.cardEspecificacaoProduto)
            cardView = view.findViewById<CardView>(R.id.card_produtos)
        }
    }
    // Quantidade de disciplinas na lista
    override fun getItemCount() = this.produtos.size
    // inflar layout do adapter
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ProdutosViewHolder {
// infla view no adapter
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_produto, parent, false)
// retornar ViewHolder
        val holder = ProdutosViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        val context = holder.itemView.context
// recuperar objeto disciplina
        val produto = produtos[position]
// atualizar dados de disciplina
        holder.cardNome.text = produto.nome
        holder.cardValor.text = produto.valor_unitario
        holder.cardEspecificacao.text = produto.especificacao


// adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(produto)}
    }
}