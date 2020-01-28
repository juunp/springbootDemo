export const FETCH_POSTS_PENDING = 'FETCH_POSTS_PENDING';
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS';
export const FETCH_POSTS_ERROR = 'FETCH_POSTS_ERROR';

export function fetchPostsSuccess (posts) {
    return {
        type: FETCH_POSTS_SUCCESS,
        posts: posts
    }
}

export function fetchPostsPending () {
    return {
        type: FETCH_POSTS_PENDING
    }
}

export function fetchPostsError(error) {
    return {
        type: FETCH_POSTS_ERROR,
        error: error
    }
}