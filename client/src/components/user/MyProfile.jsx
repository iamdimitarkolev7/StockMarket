import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';

import "../styles/MyProfileStyles.css"

const MyProfile = () => {
  const [totalMoney, setTotalMoney] = useState(0); 
  const [amount, setAmount] = useState('');
  const [transactions, setTransactions] = useState([]);
  const { id } = useParams();

  const getUserByIdRequest = async (id) => {
    try {
      const response = await fetch('http://localhost:8088/api/users/' + id, {
        method: 'GET',
      });
      const data = await response.json();
      console.log(data);
      setTotalMoney(data.data.user.userWallet.availableBalance)
    } catch (error) {
      console.log(error);
    }
  };

  const getAllTransactions = async () => {
    const response = await fetch('http://localhost:8088/api/users/transactions/' + id, {
      method: 'GET'
    });

    const data = await response.json();

    setTransactions(data.data.transactions);
  }
  
  useEffect(() => {
    getUserByIdRequest(id);
    getAllTransactions();
  }, [id, totalMoney]);

  const handleWithdraw = async () => {
    const response = await fetch('http://localhost:8088/api/users/withdraw_money/' + id, {
      method: 'POST',
      body: JSON.stringify({amount}),
      headers: {
        'Content-type': 'application/json'
      },
      credentials: 'include'
    });

    const data = await response.json();
    setTotalMoney(data.data.wallet.availableBalance);
  }

  const handleDeposit = async () => {
    const response = await fetch('http://localhost:8088/api/users/add_money/' + id, {
      method: 'POST',
      body: JSON.stringify({amount}),
      headers: {
        'Content-type': 'application/json'
      },
      credentials: 'include'
    });

    const data = await response.json();
    setTotalMoney(data.data.wallet.availableBalance);
  }

  return (
    <div className="my-profile">
      <p>My Profile</p>
      <p>Total Money: {totalMoney}$</p>
      <div className="input-container">
        <input
          type="text"
          placeholder="Enter amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <div className="button-container">
          <button onClick={handleWithdraw}>Withdraw</button>
          <button onClick={handleDeposit}>Deposit</button>
        </div>
      </div>
      <div className="transactions-table">
        <table>
          <tr>
            <th>Id</th>
            <th>Type</th>
            <th>Amount</th>
          </tr>
          {transactions.map((transaction, _) => {
            return <tr>
              <td>{transaction.transactionId}</td>
              <td>{transaction.transactionType}</td>
              <td>{transaction.value}</td>
            </tr>
          })}
        </table>
      </div>
    </div>
  );
}

export default MyProfile;