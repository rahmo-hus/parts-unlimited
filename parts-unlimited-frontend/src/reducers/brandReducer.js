import {FETCH_BRANDS} from "../actions/Types";

export default function (state = [], action) {
    switch (action.type) {
        case FETCH_BRANDS:
            return action.payload;
        default:
            return state;
    }
}
