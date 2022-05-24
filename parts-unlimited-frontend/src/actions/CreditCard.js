import axios from 'axios';
import authHeader from "../services/AuthHeader";
import {WITHDRAWAL_FAILED, WITHDRAWAL_SUCCESS} from "./Types";

const API_URL = 'http://localhost:9090/api/purchase';

export const purchase = (creditCard, products) => async dispatch => {
    const res = await axios.post(API_URL, {...creditCard, cartInfoRequests: products}, {headers: authHeader()});
    if (res.data === true) {
        dispatch({
            type: WITHDRAWAL_SUCCESS,
            payload: {
                withdrawal: true
            }
        })
    } else {
        dispatch({
            type: WITHDRAWAL_FAILED, payload: {
                withdrawal: false
            }
        });
    }
    return res;
}
