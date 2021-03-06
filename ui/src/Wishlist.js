import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";
import {Button} from "reactstrap";

class Wishlist extends Component {

  state = {
    wishlist: []
  };

  constructor(props) {
    super(props);
  }

  async reload(){
    const response = await fetch('/api/wishlist');
    const body = await response.json();
    this.setState({wishlist: body});
  }

  async componentDidMount() {
    await this.reload();
  }

  async delete(id){
    await fetch(`/api/wishlist/${id}`, {method: 'DELETE'}).then(() => this.reload());
  }

  render() {

    const wishlist = this.state.wishlist;
    const list = wishlist.map(e => {
      return (
        <tr>
          <td>{e.id}</td>
          <td>{e.game}</td>
          <td>
            <Button onClick={() => this.delete(e.id)} className="btn btn-danger">Delete</Button>
          </td>
        </tr>
      );
    });

    return (
      <div>
        <Menu {...this.props}/>
        <table style={{marginBottom: 0}} className="table table-dark">
          <thead>
          <tr>
            <th>Id</th>
            <th>Game</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          {list}
          </tbody>
        </table>
        <div align="center" width="100%" className="bg-dark">
        </div>
      </div>
    );
  }
};

function mapStateToProps(state) {
  return {
    credentials: state.credentials
  };
}

export default connect(mapStateToProps, actions)(Wishlist);
