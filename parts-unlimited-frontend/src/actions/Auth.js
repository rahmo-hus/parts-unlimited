import {
    GET_USER_DATA_SUCCESS,
    LOGIN_FAIL,
    LOGIN_SUCCESS,
    LOGOUT,
    REGISTER_FAIL,
    REGISTER_SUCCESS,
    SET_MESSAGE
} from "./Types";
import AuthService from "../services/AuthService";
import {history} from "../helpers/History";

export const register = (firstName, lastName, username, email, password, passwordRepeat) => dispatch =>{
    return AuthService.register(firstName, lastName, username, email, password, passwordRepeat).then(data=>{
        dispatch({
            type: REGISTER_SUCCESS,
            payload: {user: data}
        })
        return Promise.resolve();
    }, err=>{
        dispatch({
            type: REGISTER_FAIL,
            payload: {message: err.message}
        })
    })
}

export const login = (username, password) => (dispatch) =>{
    return AuthService.login(username, password).then(
        (data) => {
            dispatch({
                type: LOGIN_SUCCESS,
                payload: { user: data },
            });
            history.push('/cars');
            window.location.reload();
            return Promise.resolve();
        },
        (error) => {
            const message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();

            dispatch({
                type: LOGIN_FAIL
            });

            dispatch({
                type: SET_MESSAGE,
                payload: message,
            });

            return Promise.reject();
        }
    );
}

export const logout = () => (dispatch) => {
    AuthService.logout();
    history.push('/')
    dispatch({
        type: LOGOUT,
    });
};

export const whoAmI = () => (dispatch) =>{
    return AuthService.whoAmI().then(data=>{
        dispatch(success(data.data));
    })
}

function success(user) {
    return {
        type: GET_USER_DATA_SUCCESS,
        user
    }
}
