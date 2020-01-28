import * as actions from './actions';

describe('fetch posts action', () => {
    it('fetchPostsSuccess should create GET_POSTS action', () => {
        let posts = [{
            id: 5,
            userId: 12,
            title: 'sweet title',
            body: 'super content'
        }];
        expect(actions.fetchPostsSuccess(posts)).toEqual({
            type: actions.FETCH_POSTS_SUCCESS,
            posts: posts
        });
    })

    it('fetchPostsPending should create FETCH_POST_PENDING action', () => {
        expect(actions.fetchPostsPending()).toEqual({
            type: actions.FETCH_POSTS_PENDING
        });
    })

    it('fetchPostsError should create FETCH_POSTS_ERROR action', () => {
        expect(actions.fetchPostsError("error!!!")).toEqual({
            type: actions.FETCH_POSTS_ERROR,
            error: "error!!!"
        })
    })
})