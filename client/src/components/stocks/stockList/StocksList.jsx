import React, {useState, useEffect} from 'react';
import { initializeWebSocket } from '../../../utils/webSocketHandler';
import StockGrid from '../stockGrid/StockGrid';

import './StocksListStyles.css';

const initialData = {
  'AAPL': 'X',
  'MSFT': 'X',
  'TSLA': 'X',
  'BTC-USD': 'X',
  'ETH-USD': 'X'
};

const StocksList = () => {
  const [websocketData, setWebsocketData] = useState(initialData);

  const handleWebSocketMessage = (event) => {
    const message = JSON.parse(event.data);  

    setWebsocketData((prevData) => ({
      ...prevData,
      [message['s']]: message['p'] !== undefined ? parseFloat(message['p']).toFixed(2) : message['p'],
    }));
  };

  useEffect(() => {
    const socket = initializeWebSocket();
    socket.addEventListener('message', handleWebSocketMessage);

    return () => {
      socket.removeEventListener('message', handleWebSocketMessage);
      socket.close();
    };
  }, []);

  return (
    <div id='stocksList'>
      <StockGrid price={websocketData['AAPL']} symbol={'AAPL'} imageUrl='apple.png'></StockGrid>
      <StockGrid price={websocketData['MSFT']} symbol={'MSFT'} imageUrl='microsoft.png'></StockGrid>
      <StockGrid price={websocketData['TSLA']} symbol={'TSLA'} imageUrl='tesla.png'></StockGrid>
      <StockGrid price={websocketData['BTC-USD']} symbol={'BTC-USD'} imageUrl='bitcoin.png'></StockGrid>
      <StockGrid price={websocketData['ETH-USD']} symbol={'ETH-USD'} imageUrl='ethereum.png'></StockGrid>
    </div>
  )
}

export default StocksList;