import React, { useEffect, useState } from 'react'
import * as Typed from 'typed.js'
import { Link } from 'react-router-dom'
import { UserOutlined } from '@ant-design/icons'
import { Input, Space } from 'antd'
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons'
import { Row, Col } from 'antd'
import { Button } from 'antd'
import { Layout } from 'antd'
import { Typography } from 'antd'
import Cookies from 'universal-cookie'
import jwt_decode from 'jwt-decode'
import { Table, Tag } from 'antd'
import { Modal } from 'antd'
import { Form } from 'antd'

const { Column, ColumnGroup } = Table
const { Search } = Input

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

function AdminMoviePanel() {
  const [search, setSearch] = useState('')
  const [datatable, setDatatable] = useState([])
  const [isModalVisible, setIsModalVisible] = useState(false)
  const [isLoginModalVisible, setIsLoginModalVisible] = useState(false)
  const [isAddModalVisible, setIsAddModalVisible] = useState(false)
  const [modalMessage, setModalMessage] = useState('')
  const [modalEdit, setModalEdit] = useState('Actor')
  const [currentEditId, setCurrentEditId] = useState(-1)

  const [movieName, setMovieName] = useState('')
  const [actorName, setActorName] = useState('')
  const [actorSurname, setActorSurname] = useState('')
  const [directorName, setDirectorName] = useState('')
  const [directorSurname, setDirectorSurname] = useState('')
  const [year, setYear] = useState(0)
  const [length, setLength] = useState(0)
  const [description, setDescription] = useState('')

  const showModal = () => {
    setIsModalVisible(true)
  }

  const handleOk = () => {
    setIsModalVisible(false)
  }

  const handleCancel = () => {
    setIsModalVisible(false)
  }

  const handleNewMovieCancel = () => {
    setIsLoginModalVisible(false)
  }

  const showLoginModal = () => {
    setIsLoginModalVisible(true)
  }

  const addNewMovie = () => {
    setIsLoginModalVisible(false)
    addMovie()
  }

  const handleAddModalCancel = () => {
    setIsAddModalVisible(false)
  }

  const handleAddModalOk = () => {
    setIsAddModalVisible(false)
    if (modalEdit == 'Actor') addActor()
    else addDirector()

    var str = `You successfully added a new ${modalEdit}!`

    setModalMessage(str)
    setIsModalVisible(true)
  }

  const addActor = () => {
    fetch('http://localhost:8095/api/movies/update/' + currentEditId + '/addActor', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: actorName,
        surname: actorSurname,
      }),
    })
      .then((response) => {
        response.json()
      })
      .then((data) => {
        setModalMessage('You successfully added a new actor!')
        setIsModalVisible(true)
      })
      .catch(() => {
        setModalMessage('Something went wrong...')
        setIsModalVisible(true)
      })
  }

  const addDirector = () => {
    fetch('http://localhost:8095/api/movies/update/' + currentEditId + '/addDirector', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: directorName,
        surname: directorSurname,
      }),
    })
      .then((response) => {
        response.json()
      })
      .then((data) => {
        setModalMessage('You successfully added a new director!')
        setIsModalVisible(true)
      })
      .catch(() => {
        setModalMessage('Something went wrong...')
        setIsModalVisible(true)
      })
  }

  const columns = [
    { title: 'Name', dataIndex: 'name', key: 'name' },
    { title: 'Actors', dataIndex: 'actorList', key: 'actorList' },
    { title: 'Directors', dataIndex: 'directorList', key: 'directorList' },

    { title: 'Release year', dataIndex: 'release_year', key: 'release_year' },
    { title: 'Length', dataIndex: 'length', key: 'length' },
    { title: 'Rating', dataIndex: 'rating', key: 'rating' },
    {
      title: 'Edit',
      dataIndex: 'rate',
      key: 'rate',
      render: (text, record) => (
        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
          <a
            style={{ color: 'red' }}
            onClick={(e) => {
              deleteMovie(record.key, e)
            }}
          >
            Delete
          </a>
          <a onClick={(e) => setToActor(record.key, e)} style={{ color: 'black' }}>
            {' '}
            Add Actor
          </a>
          <a onClick={(e) => setToDirector(record.key, e)}> Add Director</a>
        </div>
      ),
    },
  ]

  const setToActor = (key, e) => {
    setModalEdit('Actor')
    setCurrentEditId(key)
    setIsAddModalVisible(true)
  }

  const setToDirector = (key, e) => {
    setModalEdit('Director')
    setCurrentEditId(key)
    setIsAddModalVisible(true)
  }

  const handleInputChange = (e) => {
    const { value } = e.target
    const id = e.target.name
    console.log(e.target.name)
    if (id === 'Actor name') {
      setActorName(value)
    }
    if (id === 'Director name') {
      setDirectorName(value)
    }
    if (id === 'Actor surname') {
      setActorSurname(value)
    }
    if (id === 'Director surname') {
      setDirectorSurname(value)
    }
    if (id === 'year') {
      setYear(value)
    }
    if (id === 'length') {
      setLength(value)
    }
    if (id === 'movieName') {
      setMovieName(value)
    }
    if (id === 'description') {
      setDescription(value)
    }
  }

  const addMovie = () => {
    fetch('http://localhost:8095/api/movies/save/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: movieName,
        release_year: year,
        length: length,
        description: description,
      }),
    })
      .then((response) => {
        response.json()
      })
      .then((data) => {
        setModalMessage('You successfully added a new movie!')
        setIsModalVisible(true)
      })
      .catch(() => {
        setModalMessage('Something went wrong...')
        setIsModalVisible(true)
      })
  }

  const deleteMovie = (key) => {
    fetch('http://localhost:8095/api/movies/del/' + key, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    })
      .then((response) => {
        response.json()
      })
      .then((data) => {
        var datatable_var = datatable.filter((item) => item.key !== key)
        console.log(datatable_var)
        setDatatable(datatable_var)
      })
    return datatable
  }

  const getMovies = () => {
    fetch('http://localhost:8095/api/movies/search?name=' + search, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data)
        setDatatable(data)
        console.log(datatable)
      })
      .catch(() => {
        setModalMessage('Something went wrong while fetching movies!')
        showModal()
      })
  }

  var login
  var admin_movie
  var admin_users

  var user_id = -1
  var is_admin = false
  var cookie = new Cookies()

  if (cookie.get('jwt-token')) {
    var token_info = jwt_decode(cookie.get('jwt-token'))
    var user_name = token_info['name']
    is_admin = token_info['isAdmin']
    user_id = token_info['userID']

    if (is_admin == false) {
      //window.location.href = '/'
    } else {
      admin_movie = (
        <Link to="/adminMovies">
          <Title type="secondary" level={3} style={{ margin: '12px 0px', color: '#d2e0fa' }}>
            Admin - Movies
          </Title>
        </Link>
      )
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
    //window.location.href = '/'
  }

  return (
    <Layout>
      <Modal title="Information" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
        <p>{modalMessage}</p>
      </Modal>

      <Modal title="Add new movie" visible={isLoginModalVisible} onOk={addNewMovie} onCancel={handleNewMovieCancel}>
        <Form>
          <Form.Item>
            <Input name="movieName" onChange={(e) => handleInputChange(e)} placeholder="Movie name" size="large" />
          </Form.Item>
          <Form.Item>
            <Input onChange={(e) => handleInputChange(e)} name="year" placeholder="Release year" size="large" />
          </Form.Item>
          <Form.Item>
            <Input onChange={(e) => handleInputChange(e)} name="length" placeholder="Length" size="large" />
          </Form.Item>
          <Form.Item>
            <Input onChange={(e) => handleInputChange(e)} name="description" placeholder="Description" size="large" />
          </Form.Item>
        </Form>
      </Modal>

      <Modal
        title={`Add ${modalEdit}`}
        visible={isAddModalVisible}
        onOk={handleAddModalOk}
        onCancel={handleAddModalCancel}
      >
        <Form>
          <Form.Item>
            <Input
              onChange={(e) => handleInputChange(e)}
              name={`${modalEdit} name`}
              placeholder={`${modalEdit} name`}
              size="large"
            />
          </Form.Item>
          <Form.Item>
            <Input
              onChange={(e) => handleInputChange(e)}
              name={`${modalEdit} surname`}
              placeholder={`${modalEdit} surname`}
              size="large"
            />
          </Form.Item>
        </Form>
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
          <Col span={3}>{admin_movie}</Col>
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
        <Row>
          <Col span={8} offset={8}>
            <Title level={2} style={{ color: 'white' }}>
              Movie Panel (ADMIN)
            </Title>
            <Search
              value={search}
              placeholder="input search text"
              allowClear
              enterButton="Search"
              size="large"
              onChange={(e) => {
                setSearch(e.target.value)
              }}
              onSearch={getMovies}
            />
            <Button
              type="primary"
              onClick={showLoginModal}
              style={{ marginTop: '10px', width: '100%', height: '40px' }}
            >
              Add new movie
            </Button>
          </Col>
        </Row>

        <Row style={{ marginTop: '80px', marginBottom: '20px' }}>
          <Col span={20} offset={2}>
            <Table
              columns={columns}
              expandable={{
                expandedRowRender: (record) => (
                  <span>
                    <p style={{ margin: 0 }}>{record.description}</p>
                  </span>
                ),
                rowExpandable: (record) => record.name !== 'Not Expandable',
              }}
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

export default AdminMoviePanel
