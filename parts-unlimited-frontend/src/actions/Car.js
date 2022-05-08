import {FETCH_CARS, FETCH_FILTERED_CARS, FETCH_SINGLE_CAR, FILTER_ACTIVATION} from "./Types";
import axios from 'axios';
import authHeader from "../services/AuthHeader";

const API_URL = 'http://localhost:9090/api/cars';

export const fetchCars = () => async dispatch => {
    const res = await axios.get(API_URL+'/all',{headers: authHeader()});
    dispatch({ type: FETCH_CARS, payload: res.data });
};

export const fetchCar = car => async dispatch => {
    const res = await axios.get(API_URL+`/${car.id}`, {headers: authHeader()});
    dispatch({ type: FETCH_SINGLE_CAR, payload: res.data });
};

export const fetchFilteredCars = filters => async dispatch => {
    const res = await axios.post(API_URL+'/filter' +
        '',{headers:authHeader()}, filters);
    dispatch({ type: FETCH_FILTERED_CARS, payload: res.data });
};

export function turnOnHomeFilter() {
    return {
        type: FILTER_ACTIVATION,
        payload: true
    };
};

export function turnOffHomeFilter() {
    return {
        type: FILTER_ACTIVATION,
        payload: false
    };
}


