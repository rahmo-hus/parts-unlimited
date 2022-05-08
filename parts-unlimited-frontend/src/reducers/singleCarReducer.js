import {FETCH_SINGLE_CAR} from "../actions/Types";

export default function(state = [], action) {
    switch (action.type) {
        case FETCH_SINGLE_CAR:
            return action.payload;
        default:
            return state;
    }
}
