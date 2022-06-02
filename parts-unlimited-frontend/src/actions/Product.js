import {DELETE_PRODUCT, FETCH_PRODUCTS, SAVE_PRODUCT, SAVE_PRODUCT_FAIL, SELECTED_PRODUCT} from "./Types";
import axios from 'axios';
import authHeader from "../services/AuthHeader";
import {history} from "../helpers/History";

const API_URL = 'http://localhost:9090/api/parts';

export const fetchProducts = category => async dispatch => {
    const res = await axios.get(`${API_URL}/category/${category}`, {headers: authHeader()});
    dispatch({type: FETCH_PRODUCTS, payload: res.data});
};

export const saveProduct = product => dispatch => {
    axios.post(`${API_URL}/save`, product, {headers: authHeader()}).then(() => {
        history.push('/products');
        window.location.reload();
        dispatch({type: SAVE_PRODUCT, payload: {success: true}});
    }).catch(e => {
        dispatch({type: SAVE_PRODUCT_FAIL, payload: {success: false}});
    });
}

export const fetchSingleProduct = id => async dispatch =>{
    const res = await axios.get(`${API_URL}/${id}`, {headers: authHeader()});
    dispatch ({
        type: SELECTED_PRODUCT,
        payload: res.data
    });
}

export const deleteProduct = id => async dispatch =>{
    const res = await axios.delete(`${API_URL}/${id}`, {headers: authHeader()});
    if(res.status === 204){
        dispatch({
            type: DELETE_PRODUCT,
            payload:{deletionSuccess: true}
        })
    }
}

