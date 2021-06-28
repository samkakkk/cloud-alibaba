package com.javadaily.account.util;

/**
 * <p>
 * <code>JwtOperator</code>
 * </p>
 * Description:
 * JWT工具类，提供JWT的解密加密操作
 * @author jianzh5
 * @date 2020/2/11 19:54
 */
/*@Slf4j
@Component
@RequiredArgsConstructor
public class JwtOperator {
    *//**
     * 秘钥
     * - 默认aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt
     *//*
    @Value("${secret:aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt}")
    private String secret;

    *//**
     * 有效期，单位秒
     * - 默认2周
     *//*
    private Long expirationTimeInSecond;

    *//**
     * 从token中获取claim
     * @param token token
     * @return claim
     *//*
    public Claims getClaimsFromToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error("token解析异常",e);
            throw new IllegalArgumentException("Token invalided.");
        }

    }

    *//**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     *//*
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    *//**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     *//*
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    *//**
     * 计算token的过期时间
     *
     * @return 过期时间
     *//*
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + this.expirationTimeInSecond * 1000);
    }

    *//**
     * 为指定用户生成token
     * @param claims 用户信息
     * @return token
     *//*
    public String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();


        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                // 你也可以改用你喜欢的算法
                // 支持的算法详见：https://github.com/jwtk/jjwt#features
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    *//**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     *//*
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public static void main(String[] args) {
        // 1. 初始化
        JwtOperator jwtOperator = new JwtOperator();
        jwtOperator.expirationTimeInSecond = 1209600L;
        jwtOperator.secret = "aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt";

        // 2.设置用户信息
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("id", "1");
        objectObjectHashMap.put("username", "zhangjian");

        // 测试1: 生成token
        String token = jwtOperator.generateToken(objectObjectHashMap);
        // 会生成类似该字符串的内容:
        //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6InpoYW5namlhbiIsImlhdCI6MTU4MTQyMjcyMCwiZXhwIjoxNTgyNjMyMzIwfQ.jfQdDBOB4Ik7SA6m8xAQ9pyNg_5yXurvCqg027UixZ4
        System.out.println(token);

        // 将我改成上面生成的token!!!
        String someToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6InpoYW5namlhbiIsImlhdCI6MTU4MTQyMjcyMCwiZXhwIjoxNTgyNjMyMzIwfQ.jfQdDBOB4Ik7SA6m8xAQ9pyNg_5yXurvCqg027UixZ4";
        // 测试2: 如果能token合法且未过期，返回true
        Boolean validateToken = jwtOperator.validateToken(someToken);
        System.out.println(validateToken);

        // 测试3: 获取用户信息
        Claims claims = jwtOperator.getClaimsFromToken(someToken);
        System.out.println(claims);

        // 将我改成你生成的token的第一段（以.为边界）
        String encodedHeader = "eyJhbGciOiJIUzI1NiJ9";
        // 测试4: 解密Header
        byte[] header = Base64.decodeBase64(encodedHeader.getBytes());
        System.out.println("header:"+new String(header));

        // 将我改成你生成的token的第二段（以.为边界）
        String encodedPayload = "eyJpZCI6IjEiLCJ1c2VybmFtZSI6InpoYW5namlhbiIsImlhdCI6MTU4MTQyMjcyMCwiZXhwIjoxNTgyNjMyMzIwfQ";
        // 测试5: 解密Payload
        byte[] payload = Base64.decodeBase64(encodedPayload.getBytes());
        System.out.println("payload:"+new String(payload));

        // 测试6: 这是一个被篡改的token，因此会报异常，说明JWT是安全的
        jwtOperator.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk3MzIsImV4cCI6MTU2Njc5OTMzMn0.nDv25ex7XuTlmXgNzGX46LqMZItVFyNHQpmL9UQf-aUx");

    }

}*/
