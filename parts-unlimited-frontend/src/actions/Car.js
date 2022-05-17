import {
    DELETE_CAR, FETCH_CAR_NAMES,
    FETCH_CARS,
    FETCH_FILTERED_CARS,
    FETCH_SINGLE_CAR,
    FILTER_ACTIVATION,
    SAVE_CAR_FAIL,
    SAVE_CAR_SUCCESS
} from "./Types";
import axios from 'axios';
import authHeader from "../services/AuthHeader";
import {history} from "../helpers/History";

const API_URL = 'http://localhost:9090/api/cars';

export const fetchCars = () => async dispatch => {
    const res = await axios.get(API_URL + '/all', {headers: authHeader()});
    dispatch({type: FETCH_CARS, payload: res.data});
};

export const fetchCar = id => async dispatch => {
    const res = await axios.get(API_URL + `/${id}`, {headers: authHeader()});
    dispatch({type: FETCH_SINGLE_CAR, payload: res.data});
};

export const fetchCarNames = () => async dispatch =>{
    const res = await axios.get(API_URL+'/basic', {headers: authHeader()});
    dispatch({type: FETCH_CAR_NAMES, payload: res.data});
}

export const deleteCar = id => async dispatch =>{
    await axios.delete(API_URL+`/${id}`, {headers:authHeader()});
    dispatch({type: DELETE_CAR});
}

export const saveCar = car => async dispatch => {
    axios.post(API_URL + '/save', car, {headers: authHeader()}).then(() => {
        history.push('/cars');
        window.location.reload();
        dispatch({type: SAVE_CAR_SUCCESS, payload: {success:true}});
    }).catch(e => {
        dispatch({
            type: SAVE_CAR_FAIL,
            payload: {success:false}
        })
    })
}

export const fetchFilteredCars = filters => async dispatch => {
    const res = await axios.post(API_URL + '/filter' +
        '', filters, {headers: authHeader()});
    dispatch({type: FETCH_FILTERED_CARS, payload: res.data});
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


