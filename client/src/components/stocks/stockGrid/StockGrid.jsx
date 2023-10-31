import React from 'react';

import './StockGridStyles.css';
import TradeButton from '../../buttons/tradeButton/TradeButton';

const StockGrid = ({imageUrl, price, symbol}) => {

  let assetName = '';

  switch (symbol) {
    case 'AAPL': assetName = 'Apple'; break;
    case 'MSFT': assetName = 'Microsoft'; break;
    case 'TSLA': assetName = 'Tesla'; break;
    case 'BTC-USD': assetName = 'Bitcoin'; break;
    case 'ETH-USD': assetName = 'Ethereum'; break;
    default: break;
  }

  return (
    <div className='stockBox'>
      <h3 className='title'>{assetName}</h3>
      <div className='asset-icon'>
        <img src={imageUrl} alt={symbol}></img>
      </div>
      <p className='symbol'>{symbol}</p>
      <p className='price'>${price}</p>
      <TradeButton title='Buy'></TradeButton>
      <TradeButton title='Sell'></TradeButton>
    </div>
  )
}

export default StockGrid;