/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matheusfroes
 */
@Stateless
public class ItemPedidoFacade extends AbstractFacade<ItemPedido> {

    @PersistenceContext(unitName = "YcecocoSitePU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ItemPedidoFacade() {
        super(ItemPedido.class);
    }
    
}
