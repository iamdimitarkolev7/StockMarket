let socket = null;

export function initializeWebSocket() {
  if (socket) {
    return socket;
  }
  socket = new WebSocket('ws://ws.eodhistoricaldata.com/ws/us?api_token=demo');
  socket.onopen = onOpen;
  socket.onmessage = onMessage;
  socket.onclose = onClose;

  return socket;
}

function onOpen(event) {
  console.log('WebSocket connection opened:', event);
  socket.send('{"action": "subscribe", "symbols": "AAPL, MSFT, TSLA, ETH-USD, BTC-USD"}');
}

function onMessage(event) {
  //console.log('Received WebSocket message:', event.data);
}

function onClose(event) {
  console.log('WebSocket connection closed:', event);
}

export function sendWebSocketMessage(message) {
  if (socket && socket.readyState === WebSocket.OPEN) {
    socket.send(message);
  }
}