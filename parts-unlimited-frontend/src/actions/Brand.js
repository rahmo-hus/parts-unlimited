import axios from "axios";
import authHeader from "../services/AuthHeader";
import {FETCH_BRANDS} from "./Types";

const API_URL = 'http://localhost:9090/api/brands';

export const fetchBrands = () => async dispatch => {
    const res = await axios.get(API_URL,{headers: authHeader()});
    dispatch({ type: FETCH_BRANDS, payload: res.data });
};
