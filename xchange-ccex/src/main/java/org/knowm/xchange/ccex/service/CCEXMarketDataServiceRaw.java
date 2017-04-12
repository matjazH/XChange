package org.knowm.xchange.ccex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ccex.CCEX;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.ccex.dto.ticker.CCEXPriceResponse;
import org.knowm.xchange.ccex.dto.ticker.CCEXTickerResponse;
import org.knowm.xchange.ccex.dto.marketdata.CCEXTrades;
import org.knowm.xchange.ccex.dto.marketdata.CCEXMarket;
import org.knowm.xchange.ccex.dto.marketdata.CCEXGetorderbook;

import java.util.List;
import java.io.IOException;

/**
 * @author Andraž Prinčič
 */
public class CCEXMarketDataServiceRaw extends CCEXBaseService {

    private final CCEX ccex;

    public List<CCEXMarket> getConbaseExProducts() throws IOException {
        return ccex.getProducts().getResult();
    }

    public CCEXTrades getCCEXTrades(CurrencyPair pair) throws IOException {
        return ccex.getTrades(new CCEX.Pair(pair));
    }

    public CCEXGetorderbook getCCEXOrderBook(CurrencyPair pair, int depth) throws IOException {
        return ccex.getOrderBook(new CCEX.Pair(pair), depth);
    }

    public CCEXMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
        this.ccex = RestProxyFactory.createProxy(CCEX.class,exchange.getExchangeSpecification().getSslUri());
    }

    public CCEXPriceResponse getTicker(CurrencyPair pair) throws IOException {
        CCEXTickerResponse response;
        response = ccex.getTicker(pair.base.toString().toLowerCase(), pair.counter.toString().toLowerCase());

        return response.getTicker();
    }

    public enum CCEXTime {
        DAY, HOUR, MINUTE;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
