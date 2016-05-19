package com.xeiam.xchange.bitbay.service.polling;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.bitbay.BitbayAdapters;
import com.xeiam.xchange.bitbay.dto.account.BitbayAccount;
import com.xeiam.xchange.currency.Currency;
import com.xeiam.xchange.dto.account.AccountInfo;
import com.xeiam.xchange.exceptions.ExchangeException;
import com.xeiam.xchange.exceptions.NotAvailableFromExchangeException;
import com.xeiam.xchange.exceptions.NotYetImplementedForExchangeException;
import com.xeiam.xchange.service.polling.account.PollingAccountService;

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
