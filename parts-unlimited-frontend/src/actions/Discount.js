import axios from "axios";
import authHeader from "../services/AuthHeader";
import {ADD_DISCOUNT, DELETE_DISCOUNT} from "./Types";

const API_URL = 'http://localhost:9090/api/discounts';

export const addDiscount = (discount) => async dispatch =>{
    const res = await axios.post(API_URL+"/save", discount ,{headers:authHeader()});
    if(res.status === 204){
        dispatch({
            type:ADD_DISCOUNT,
            payload: {
                success:true
            }
        })
    }
}

export const deleteDiscount = (id) => async dispatch =>{
    const res = await axios.delete(API_URL+`/${id}`, {headers: authHeader()});
    if(res.status === 200) {
        dispatch({
            type:DELETE_DISCOUNT
        })
    }
}
