import { FETCH_POSTS_SUCCESS, FETCH_POSTS_PENDING, FETCH_POSTS_ERROR } from './actions';

const initialState = {
    pending: false,
    posts: [],
    error: null
};


export default function posts(state = initialState, action) {
    switch (action.type) {
        case FETCH_POSTS_PENDING: 
            return {
                ...state,
                pending: true
            }
        case FETCH_POSTS_SUCCESS: 
           return {
                ...state,
                posts: action.posts,
                pending: false
           }
        case FETCH_POSTS_ERROR:
            return {
                ...state,
                error: action.error,
                pending: false
            }
        default:
            return state;
    }
}

export const getPosts = state => state.posts;
export const getPostsPending = state => state.pending;
export const getPostsError = state => state.error;