import {FETCH_CAR_NAMES} from "../actions/Types";

export default function (state = [], action){
    switch (action.type){
        case FETCH_CAR_NAMES:
            return action.payload;
        default:
            return state;
    }
}
