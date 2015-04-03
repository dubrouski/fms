package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.model.Price;

@Stateless
@Named(value = "priceDao")
public class PriceDaoImpl extends BaseDaoImpl<Price, Long> implements PriceDao{

}
