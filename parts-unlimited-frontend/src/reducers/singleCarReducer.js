import {FETCH_SINGLE_CAR, FETCH_SINGLE_CAR_PROGRESS} from "../actions/Types";

export default function(state = [], action) {
    switch (action.type) {
        case FETCH_SINGLE_CAR:
            return action.payload;
        case FETCH_SINGLE_CAR_PROGRESS:
            return action.payload;
        default:
            return state;
    }
}
