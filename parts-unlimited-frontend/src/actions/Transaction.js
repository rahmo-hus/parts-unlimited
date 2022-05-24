import authHeader from "../services/AuthHeader";
import axios from 'axios';

import {FETCH_TRANSACTIONS, FETCH_TRANSACTIONS_BY_USER} from "./Types";
const API_URL = 'http://localhost:9090/api/transactions';

export const fetchTransactions = () => async dispatch =>{
    const res = await axios.get(API_URL, {headers: authHeader()});
    dispatch({
        type: FETCH_TRANSACTIONS,
        payload: res.data
    });
}

export const findAllTransactionsByUserId = userId => async dispatch =>{
    const res = await axios.get(API_URL+'/get-by-user/'+userId, {headers: authHeader()});
    dispatch({
        type: FETCH_TRANSACTIONS_BY_USER,
        payload: res.data
    });
}
