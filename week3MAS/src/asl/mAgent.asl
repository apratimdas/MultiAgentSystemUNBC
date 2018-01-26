
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
		
					
+!moveTo(X, Y)
	:	position(I, J) & I > X & not all_clear(I-1, J)
	<-	left;
		.print("Left is blocked!!");
		!declareLocation;
		!moveTo(X, Y).
		
+!moveTo(X, Y)
	:	position(I, J) & I < X & not all_clear(I+1, J)
	<-	right;
		.print("moved right.");
		!declareLocation;
		!moveTo(X, Y).

+!moveTo(X, Y)
	:	position(I, J) & J > Y & not all_clear(I, J-1)
	<-	up;
		.print("moved up.");
		!declareLocation;
		!moveTo(X, Y).
		
+!moveTo(X, Y)
	:	position(I, J) & J < Y & not all_clear(I, J+1)
	<-	down;
		.print("moved down.");
		!declareLocation;
		!moveTo(X, Y).
				
				
				
					
+!moveTo(X, Y)
	:	position(I, J) & I > X & all_clear(I-1, J)
	<-	left;
		.print("moved left.");
		!declareLocation;
		!moveTo(X, Y).
		
+!moveTo(X, Y)
	:	position(I, J) & I < X & all_clear(I+1, J)
	<-	right;
		.print("moved right.");
		!declareLocation;
		!moveTo(X, Y).

+!moveTo(X, Y)
	:	position(I, J) & J > Y & all_clear(I, J-1)
	<-	up;
		.print("moved up.");
		!declareLocation;
		!moveTo(X, Y).
		
+!moveTo(X, Y)
	:	position(I, J) & J < Y & all_clear(I, J+1)
	<-	down;
		.print("moved down.");
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
		
		
		
		
		
		
		
		
		