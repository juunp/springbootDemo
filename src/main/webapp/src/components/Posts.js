import React, { Component } from 'react';
import { bindActionCreators } from 'redux'; 
import { connect } from 'react-redux';
import { getPosts, getPostsPending, getPostsError } from '../reducer';
import fetchPostsAction from '../fetchPosts'

export class Posts extends Component {

    componentDidMount () {
        const { fetchPosts } = this.props;
        fetchPosts();
    }
    
    render() {
        const { posts } = this.props;
        return (
            <ol>
                { null !== posts && undefined !== posts && posts.map( post => <li className="post" key={post.id}>
                    <section>
                        <h2>{post.title}</h2>
                        <p>{post.body}</p>
                    </section>
                </li>)}
            </ol>
        )
    }
}

const mapStateToProps = state => ({
    posts: getPosts(state),
    pending: getPostsPending(state),
    error: getPostsError(state)
});
  
const mapDispatchToProps = dispatch => bindActionCreators({
    fetchPosts: fetchPostsAction
}, dispatch)


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Posts);