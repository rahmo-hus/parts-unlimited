import {GET_USER_DATA_SUCCESS, LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT} from '../actions/Types'

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
        case LOGIN_FAIL:
            return {};
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
