import React from 'react';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';  
import reducer from './reducer';
import Posts from './components/Posts';
import thunkMiddleware from 'redux-thunk';
import './App.css';

const store = createStore(reducer, applyMiddleware(thunkMiddleware));

function App() {
  return (
    <Provider store={store}>
      <div className="App">   
        <Posts posts={null}/>
      </div>
    </Provider>
  );
}

export default App;
