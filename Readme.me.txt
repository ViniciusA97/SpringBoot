Pegar token - 
	localhost:8080/oauth/token
	encode 64 spring boot
	username - email do usuario
	password - senha do usuario cripada com Bcrypt
	grant_type - password

EndPoint que necessitam de autenticação
	Header: Authorization Bearer <token>
	