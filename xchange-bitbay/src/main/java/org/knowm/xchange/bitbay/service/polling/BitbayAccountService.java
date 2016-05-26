package org.knowm.xchange.bitbay.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitbay.BitbayAdapters;
import org.knowm.xchange.bitbay.dto.account.BitbayAccount;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author yarkh
 */
public class BitbayAccountService extends BitbayAccountServiceRaw implements PollingAccountService {

    public BitbayAccountService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public AccountInfo getAccountInfo() throws IOException {
        BitbayAccount bitbayAccountInfo = getBitbayAccountInfo(null);
        if (bitbayAccountInfo.getSuccess() == null) {
            throw new ExchangeException(bitbayAccountInfo.getMessage());
        }
        return BitbayAdapters.adaptAccount(bitbayAccountInfo);
    }

    @Override
    public String withdrawFunds(Currency currency, BigDecimal amount, String address) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
        return null;
    }

    @Override
    public String requestDepositAddress(Currency currency, String... args) throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
        BitbayAccount bitbayAccountInfo = getBitbayAccountInfo(currency.getCurrencyCode());
        return bitbayAccountInfo.getAddresses().get(currency);
    }
}
