import {fetchPostsPending, fetchPostsSuccess, fetchPostsError} from './actions';

function fetchPosts() {
    return dispatch => {
        dispatch(fetchPostsPending());
        fetch('/api/v10/posts')
        .then(res => res.json())
        .then(res => {
            if(res.error) {
                throw(res.error);
            }
            dispatch(fetchPostsSuccess(res));
            return res.products;
        })
        .catch(error => {
            dispatch(fetchPostsError(error));
        })
    }
}

export default fetchPosts;