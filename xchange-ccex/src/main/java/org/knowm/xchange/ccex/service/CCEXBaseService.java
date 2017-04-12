package org.knowm.xchange.ccex.service;

import org.knowm.xchange.Exchange;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
import org.knowm.xchange.ccex.CCEXAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

/**
 * @author Andraž Prinčič
 */
public class CCEXBaseService extends BaseExchangeService implements BasePollingService {

    protected final String apiKey;
    protected final ParamsDigest signatureCreator;
    protected final CCEXAuthenticated cCEXAuthenticated;

    /**
     * Constructor
     *
     * @param exchange
     */
    public CCEXBaseService(Exchange exchange) {

        super(exchange);

        this.cCEXAuthenticated = RestProxyFactory.createProxy(CCEXAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
        this.apiKey = exchange.getExchangeSpecification().getApiKey();
        this.signatureCreator = CCEXDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    }
}
