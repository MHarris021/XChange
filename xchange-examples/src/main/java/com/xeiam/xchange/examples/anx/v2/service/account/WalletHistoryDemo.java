/**
 * Copyright (C) 2012 - 2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.xchange.examples.anx.v2.service.account;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.examples.anx.v2.ANXExamplesUtils;
import com.xeiam.xchange.anx.ANXUtils;
import com.xeiam.xchange.anx.v2.ANXV2;
import com.xeiam.xchange.anx.v2.dto.ANXException;
import com.xeiam.xchange.anx.v2.dto.account.polling.ANXWalletHistoryEntry;
import com.xeiam.xchange.anx.v2.dto.account.polling.ANXWalletHistoryWrapper;
import com.xeiam.xchange.anx.v2.service.ANXV2Digest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;

/**
 * Demo requesting wallethistory at ANX
 */
public class WalletHistoryDemo {

    public static void main(String[] args) throws ANXException, IOException {

        Exchange ANXExchange = ANXExamplesUtils.createExchange();
        ANXV2 ANXV2 = RestProxyFactory.createProxy(ANXV2.class, ANXExchange.getExchangeSpecification().getSslUri());
        ParamsDigest signatureCreator = ANXV2Digest.createInstance(ANXExchange.getExchangeSpecification().getSecretKey());

        ANXWalletHistoryWrapper wallethistory = ANXV2.getWalletHistory(ANXExchange.getExchangeSpecification().getApiKey(), signatureCreator, ANXUtils.getNonce(), "BTC", null);

        System.out.println("WalletHistory: " + wallethistory.getANXWalletHistory().toString());
        for (ANXWalletHistoryEntry entry : wallethistory.getANXWalletHistory().getANXWalletHistoryEntries()) {
            System.out.println(entry.toString());
        }
    }
}
