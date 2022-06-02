import {
    GET_USER_DATA_SUCCESS,
    LOGIN_FAIL,
    LOGIN_REQUEST,
    LOGIN_SUCCESS,
    LOGOUT, REGISTER_FAIL,
    REGISTER_SUCCESS,
    SET_MESSAGE
} from '../actions/Types'

let user = JSON.parse(localStorage.getItem('user'));

const initialState = user ? { loggedIn: true, user } : {};

export function authentication(state = initialState, action) {
    switch (action.type) {
        case LOGIN_REQUEST:
            return {
                loggingIn: true,
                user: action.user
            };
        case LOGIN_SUCCESS:
            return {
                loggedIn: true,
                user: action.user
            };
        case REGISTER_SUCCESS:
            return{
                registered: true,
                user:action.user
            };
        case REGISTER_FAIL:
            return {
                registered: false
            }
        case LOGIN_FAIL:
            return {};
        case SET_MESSAGE:
            return {message:action.payload}
        case LOGOUT:
            return {};
        case GET_USER_DATA_SUCCESS:
            return {
                user: action.user
            }
        default:
            return state
    }
}
