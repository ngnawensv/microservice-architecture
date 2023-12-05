# CIRCUIT BREAKER
## Problem solved by Circuit Breaker pattern
### Context
1. Considering 1 Client **C** and  4 microservices **M1**, **M2**,**M3** and **M4**;  
2. Assume that **C** call , **M1** call **M2**, **M2** call **M3** and **M3** call **M4**;  
3.  When Client call M1, M1 called M2 internally, M2 call M3 and M3 call M4;  
4.  For any raison , assume the M4 is down then M3,M2 and M1 won't work properly;  
5. If M4 is slow then M3,M2 and M1 will be infected or impacted

### Solution
1. **Implemented fallback method**   
For example if M4 is down, the fallback method implemented in M3 will return default response to M2 and then
M2 and M1 will get that response.  
2.  **Implemented circuit breaker pattern**  
For example when M4 is down the circuit breaker will not allow M3 to continuously call M4   
3.  **Implemented retry mecanism**   
For example if M4 is temporary down,M3 will retry multiple time to connect to M4. but if up we will get 
successful response.  
4. **Implemented rateLimit**  
this pattern will limit the number call from M3 to m4 for example

### Circuit Breaker (CB) Pattern works
 The CB is implemented via a finite state machine with three normal states: CLOSED,OPEN and HALF_OPEN 
 and two special states DISABLE and FORCED_OPEN.  
 For Example, if we have 2 Micro services M1 and M2. Assume the CB is implemented on the M1.  

The **CLOSED** state means CB will allow the request to flow from M1 to M2.   
Here we basically maintain the threshold, let's say 50% (50% means if the failure rate exceed the threshold, then the CB will  
move into OPEN state).
Example: M1 send 5 requests to M2, if we have 2 success requests and 3 failures requests, the CB will move to OPEN state.  

In **OPEN** state, we can set the time limit, the CB will remain 5 seconds in OPEN state, and after that, it will automatically
move to HALF-OPEN state.

In the **HALF-OPEN** state, the circuit breaker will allow limited number of calls N, M1 will make N calls, if it got success the CB
will move to CLOSED state else it will move to OPEN state.

### Circuit Breaker (CB) Pattern implementation using Resilience4j steps
1. Add dependencies
2. Using @CircuitBreaker annotation to a method
3. Fallback method implementation
4. Add Circuit Breaker configuration in application.yml file
5. Restart agro-service and demo


### Retry Pattern implementation using Resilience4j steps
1. Using @CircuitBreaker annotation to a method
2. Fallback method implementation
3. Add Retry configuration in application.yml file
4. Restart agro-service and demo