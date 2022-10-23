import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Space } from 'antd'
import { Row, Col } from 'antd'
import { Button } from 'antd'
import { Layout } from 'antd'
import { Typography } from 'antd'
import Cookies from 'universal-cookie'
import jwt_decode from 'jwt-decode'
import { Table, Tag } from 'antd'
import { Modal } from 'antd';



const { Header, Footer, Sider, Content } = Layout
const { Title, Text } = Typography

function timeout(delay) {
  return new Promise((res) => setTimeout(res, delay))
}

async function changeWindow() {
  await timeout(500)
  var cookie = new Cookies()
  if (cookie.get('jwt-token')) {
    window.location.reload()
  }
}

function logout() {
  var cookie = new Cookies()
  cookie.remove('jwt-token')
  window.location.href = '/'
  window.location.reload()
}


function AdminUserPanel() {
  const [datatable, setDatatable] = useState([])
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [modalMessage, setModalMessage] = useState("")

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  const columns = [
    { title: 'Email', dataIndex: 'email', key: 'email' },
    { title: 'Name', dataIndex: 'name', key: 'name' },
    { title: 'Surname', dataIndex: 'surname', key: 'surname' },
    { title: 'Role', dataIndex: 'role', key: 'role' },
    {
      title: 'Delete this user', dataIndex: 'delete', key: 'delete', render: (text, record) => (
        <a
          onClick={(e) => { onDelete(record.key, e); }}
        >
          Delete
        </a>
      )
    }
  ]



  function onDelete(key) {
    deleteUser(key)
    /*var datatable_var = datatable.filter(item => item.key !== key);
    console.log(datatable_var);
    setDatatable(datatable_var);*/
  }

  function deleteUser(key) {
    if (key == user_id) {
      setModalMessage("You can't delete yourself!")
      setIsModalVisible(true)
      return
    }
    fetch('http://localhost:8095/api/users/del/' + key, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    })
      .then((data) => {
        var datatable_var = datatable.filter(item => item.key !== key);
        setDatatable(datatable_var)
        setModalMessage("User successfully deleted!")
        setIsModalVisible(true)
      }).catch(() => {
        setModalMessage("Something went wrong!");
        setIsModalVisible(true);
      })
    return datatable
  }

  function getUsers() {
    fetch('http://localhost:8095/api/users/get/all', {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setDatatable(data)
        console.log(datatable)
      }).catch(() => {
        setModalMessage("Something went wrong while fetching users!");
        setIsModalVisible(true);
      })
  }

  var login
  var user_id = -1
  var is_admin = false
  var cookie = new Cookies()

  if (cookie.get('jwt-token')) {
    var token_info = jwt_decode(cookie.get('jwt-token'))
    var user_name = token_info['name']
    is_admin = token_info['isAdmin']
    user_id = token_info['userID']

    if (is_admin == false) {
      window.location.href = '/'
    }

    login = (
      <Space size="large">
        <Title level={5} style={{ color: 'white' }}>
          Welcome back {user_name}!
        </Title>
        <Button
          type="secondary"
          size="middle"
          style={{ backgroundColor: '#164c7e', color: 'white', border: '1px solid #111d2c' }}
          onClick={logout}
        >
          Logout
        </Button>
      </Space>
    )
  } else {
    window.location.href = '/'
  }


  return (
    <Layout>

      <Modal title="Information" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
        <p>{modalMessage}</p>
      </Modal>

      <Header style={{ backgroundColor: '#1a1325', borderBottom: '2px solid #301c4d', height: '7vh' }}>
        <Row>
          <Col span={3}>
            <Link to="/">
              <Title type="danger" style={{ margin: '5px 0px', color: '#d89614' }}>
                Curtain
              </Title>
            </Link>
          </Col>
          <Col span={2}>
            <Link to="/">
              <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                Home
              </Title>
            </Link>
          </Col>
          <Col span={2}>
            <Link to="/movies">
              <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                Movies
              </Title>
            </Link>
          </Col>
          <Col span={3}>
            <Link to="/adminMovies">
              <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                Admin - Movies
              </Title>
            </Link>
          </Col>
          <Col span={3}>
            <Link to="/adminUsers">
              <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
                Admin - Users
              </Title>
            </Link>
          </Col>
          <Col
            span={7}
            offset={4}
            style={{
              display: 'flex',
              alignItems: 'end',
              justifyContent: 'flex-end',
              flexDirection: 'row',
            }}
          >
            {login}
          </Col>
        </Row>
      </Header>
      <Content
        style={{
          backgroundColor: '#131629',
          display: 'flex',
          flexDirection: 'column',
          height: '85vh',
          paddingTop: '5vh',
        }}
      >
        <Row style={{ display: "flex" }}>
          <Col span={8} offset={8} justify='center'>
            <Title level={2} style={{ color: 'white' }}>
              User Panel (ADMIN)
            </Title>
            <Button style={{ width: "100%" }} size='large' type='primary' onClick={getUsers}>GET USERS</Button>
          </Col>
        </Row>

        <Row style={{ marginTop: '80px', marginBottom: '20px' }}>
          <Col span={20} offset={2}>
            <Table
              columns={columns}
              dataSource={datatable}
              pagination={{ defaultPageSize: 5 }}
            />
          </Col>
        </Row>
      </Content>
      <Footer style={{ backgroundColor: '#1a1325', borderTop: '2px solid #301c4d', height: '8vh' }}>
        <Row justify="end">
          <Text style={{ color: '#13c2c2' }}>© 2022 - Created by Marco Born</Text>
        </Row>
      </Footer>
    </Layout>
  )
}

export default AdminUserPanel
