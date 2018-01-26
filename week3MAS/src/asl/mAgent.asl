
all_clean :- not dirt(_, _).
all_clear :- not blocked(_, _).

!clean.

+!clean
	:	all_clean
	<-	.print("We are all done here!").
	
+!clean
	:	not all_clean
	<-	?dirt(X, Y);
		.print("targeting dirt at: ", X, ", ", Y);
		!moveTo(X, Y);
		suck;
		.print("sucked the dirt.");
		!clean.
		
		
/* Left Check */
					
+!moveTo(X, Y)
	:	position(I, J) & I > X & not blocked(I-1, J)
	<-	left;
		.print("moved left.");
		!declareLocation;
		!moveTo(X, Y).
		

+!moveTo(X, Y)
	:	position(I, J) & I > X & blocked(I-1, J)
	<-	.print("Left is blocked!!");
		destroy(I-1,J);
		.print("Destroyed left block");
		!declareLocation;
		!moveTo(X, Y).


/* Right Check */

+!moveTo(X, Y)
	:	position(I, J) & I < X & not blocked(I+1, J)
	<-	right;
		.print("moved right.");
		!declareLocation;
		!moveTo(X, Y).
						
		
+!moveTo(X, Y)
	:	position(I, J) & I < X & blocked(I+1, J)
	<-	.print("Right is blocked!! at: ", I+1, ", ", J);
		destroy(I+1,J);
		.print("Destroyed right block");
		!declareLocation;
		!moveTo(X, Y).
		

/* Top Check */

+!moveTo(X, Y)
	:	position(I, J) & J > Y & not blocked(I, J-1)
	<-	up;
		.print("moved up.");
		!declareLocation;
		!moveTo(X, Y).

					
+!moveTo(X, Y)
	:	position(I, J) & J > Y & blocked(I, J-1)
	<-	.print("Top is blocked!!");
		destroy(I,J-1);
		.print("Destroyed top block");
		!declareLocation;
		!moveTo(X, Y).
		

/* Bottom Check */

+!moveTo(X, Y)
	:	position(I, J) & J < Y & not blocked(I, J+1)
	<-	down;
		.print("moved down.");
		!declareLocation;
		!moveTo(X, Y).
	

+!moveTo(X, Y)
	:	position(I, J) & J < Y & blocked(I, J+1)
	<-	.print("Bottom is blocked!!");
		destroy(I,J+1);
		.print("Destroyed bottom block");
		!declareLocation;
		!moveTo(X, Y).
				



+!moveTo(X, Y)
	:	position(I, J) & X == I & J == Y
	<-	.print("ARRIVED.").		
		
+!declareLocation
	: 	true
	<-	?position(X, Y);
		.print("I am at: ", X, ", ", Y).
		
+!destroy(X,Y)
	: 	true
	<- 	?position(X, Y);
		.print("I am at: ", X, ", ", Y).
		
		
		
		
		
		
		
		
		