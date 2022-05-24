import {WITHDRAWAL_FAILED, WITHDRAWAL_SUCCESS} from "../actions/Types";

export default function (state = [], action) {
    switch (action.type) {
        case WITHDRAWAL_SUCCESS:
            return action.payload;
        case WITHDRAWAL_FAILED:
            return action.payload;
        default:
            return state;
    }
}
