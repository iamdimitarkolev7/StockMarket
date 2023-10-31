import React from 'react';

import './TradeButtonStyles.css';

const TradeButton = ({title, onClickFn}) => {

  return (
    <button className='tradeBtn' onClick={onClickFn}>{title}</button>
  )
}

export default TradeButton;