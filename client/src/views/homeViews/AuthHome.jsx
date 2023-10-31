import React from 'react';
import StocksList from '../../components/stocks/stockList/StocksList';

const AuthHome = () => {

  return (
    <div>
      <h1>Stocks that InvestoMania suggests:</h1>
      <StocksList></StocksList>
    </div>
  );
};

export default AuthHome;