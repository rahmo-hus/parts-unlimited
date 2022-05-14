import {FETCH_PRODUCTS, SELECTED_PRODUCT} from "./Types";
import axios from 'axios';
const API_URL = 'http://localhost:9090/api/parts/';

export const fetchProducts = category => async dispatch => {
    const res = await axios.get(`${API_URL}category/${category}`);
    dispatch({ type: FETCH_PRODUCTS, payload: res.data });
};
export function fetchSingleProduct(product) {
    return {
        type: SELECTED_PRODUCT,
        payload: product
    };
}

