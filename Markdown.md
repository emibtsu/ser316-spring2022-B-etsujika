Code Review Defect List
	
	
| ID # | Location | Problem Description | Problem | File and Line Number | Category | Severity | 
|---|---|---|---|--|--|--|
| 1 | Beginning of All classes | There is no File banner for any classes | Missing File Banner | All files, line 1 | CG | Low | 
| 2 | Beginning of methods | All methods and constructors dont have a '{' on its own line | '{' need own line | All files, all methods/constructors at 1st line | CG | Low | 
| 3 | Cart | The program adds $3 to the total cost instead of Subtracting it | Adding savings instead of subtracting | Cart.java, 71 | FD | BR | 
| 4 | Cart | Name of variable is not descriptive enough, np is not concise enough | Need better param name | Cart.java, 101 & 103 | CS | Low | 
| 5 | classes that extend produce | Classes perform identical operations such as get cost and the setting cost, could just make enum types in produce and eliminate extra classes | Classes do too little | FrozenFood, Meat, Dairy, Alcohol, Produce | CS | Low | 
| 6 | UnderAgeException | It passes the excpetion to set, but it is always going to be the same so paramater of error message is unnecessary | Remove unnec | UnderAgeExcpetion. 4 | CS | Low | 
| 7 | Cart | Method returns the total as the amount to add to as tax for a default state, so it just doubles the amount instead of adding tax | Fix default tax calulations | Cart.java, 96 | FD | MJ | 



Category:	CS – Code Smell defect. CG – Violation of a coding guideline. Provide the guideline number. FD – Functional defect. Code will not produce the expected result. MD – Miscellaneous defect, for all other defects.
Severity:       BR - Blocker, must be fixed asap. MJ – Major, of high importance but not a Blocker LOW – Low. 
