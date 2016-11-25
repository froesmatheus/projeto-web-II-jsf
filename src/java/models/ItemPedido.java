
package models;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Created by matheus on 05/07/16.
 */
@Named(value = "itemPedido")
@Entity
@RequestScoped
@Table(name = "ItensPedido")
public class ItemPedido implements Serializable {
    @OneToOne
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public ItemPedido() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.produto.getDescricao() + "      x" + this.quantidade+"\n"; 
    }
    
    
}
