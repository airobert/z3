/**
 * This file was automatically generated from AST.cs 
 **/

package com.Microsoft.Z3;

/* using System; */
/* using System.Collections; */
/* using System.Collections.Generic; */

    /**
     * The abstract syntax tree (AST) class. 
     **/
    public class AST extends Z3Object
    {
        /**
         * Comparison operator.
         * <param name="a">An AST</param>
         * <param name="b">An AST</param>
         * @return True if <paramref name="a"/> and <paramref name="b"/> are from the same context 
         * and represent the same sort; false otherwise.
         **/
         /* Overloaded operators are not translated. */

        /**
         * Comparison operator.
         * <param name="a">An AST</param>
         * <param name="b">An AST</param>
         * @return True if <paramref name="a"/> and <paramref name="b"/> are not from the same context 
         * or represent different sorts; false otherwise.
         **/
         /* Overloaded operators are not translated. */

        /**
         * Object comparison.
         **/
        public boolean Equals(object o)
        {
            AST casted = (AST) o;
            if (casted == null) return false;
            return this == casted;
        }

        /**
         * Object Comparison.
         * <param name="other">Another AST</param>
         * @return Negative if the object should be sorted before <paramref name="other"/>, positive if after else zero.
         **/
        public int CompareTo(object other)
        {
            if (other == null) return 1;
            AST oAST = (AST) other;
            if (oAST == null)
                return 1;
            else
            {
                if (Id < oAST.Id)
                    return -1;
                else if (Id > oAST.Id)
                    return +1;
                else
                    return 0;
            }
        }

        /**
         * The AST's hash code.
         * @return A hash code
         **/
        public int GetHashCode()
        {
            return (int)Native.getAstHash(Context.nCtx, NativeObject);
        }

        /**
         * A unique identifier for the AST (unique among all ASTs).
         **/
        public Integer Id()  { return Native.getAstId(Context.nCtx, NativeObject); }

        /**
         * Translates (copies) the AST to the Context <paramref name="ctx"/>.
         * <param name="ctx">A context</param>
         * @return A copy of the AST which is associated with <paramref name="ctx"/>
         **/
        public AST Translate(Context ctx)
        {
            
            

            if (ReferenceEquals(Context, ctx))
                return this;
            else
                return new AST(ctx, Native.translate(Context.nCtx, NativeObject, ctx.nCtx));
        }

        /**
         * The kind of the AST.
         **/
        public Z3_ast_kind ASTKind()  { return (Z3AstKind)Native.getAstKind(Context.nCtx, NativeObject); }

        /**
         * Indicates whether the AST is an Expr
         **/
        public boolean IsExpr() 
            {
                switch (ASTKind)
                {
                    case Z3AstKind.Z3APPAST:
                    case Z3AstKind.Z3NUMERALAST:
                    case Z3AstKind.Z3QUANTIFIERAST:
                    case Z3AstKind.Z3VARAST: return true;
                    default: return false;
            }
        }

        /**
         * Indicates whether the AST is a BoundVariable
         **/
        public boolean IsVar()  { return this.ASTKind == Z3AstKind.Z3VARAST; }

        /**
         * Indicates whether the AST is a Quantifier
         **/
        public boolean IsQuantifier()  { return this.ASTKind == Z3AstKind.Z3QUANTIFIERAST; }

        /**
         * Indicates whether the AST is a Sort
         **/
        public boolean IsSort()  { return this.ASTKind == Z3AstKind.Z3SORTAST; }

        /**
         * Indicates whether the AST is a FunctionDeclaration
         **/
        public boolean IsFuncDecl()  { return this.ASTKind == Z3AstKind.Z3FUNCDECLAST; }

        /**
         * A string representation of the AST.
         **/
        public String toString()
        {
            return Native.asttoString(Context.nCtx, NativeObject);
        }

        /**
         * A string representation of the AST in s-expression notation.
         **/
        public String SExpr()
        {
            

            return Native.asttoString(Context.nCtx, NativeObject);
        }

        AST(Context ctx) { super(ctx);  }
        AST(Context ctx, IntPtr obj) { super(ctx, obj);  }

        class DecRefQueue extends Z3.DecRefQueue
        {
            public void IncRef(Context ctx, IntPtr obj)
            {
                Native.incRef(ctx.nCtx, obj);
            }

            public void DecRef(Context ctx, IntPtr obj)
            {
                Native.decRef(ctx.nCtx, obj);
            }
        };        

        void IncRef(IntPtr o)
        {            
            // Console.WriteLine("AST IncRef()");
            if (Context == null)
                throw new Z3Exception("inc() called on null context");
            if (o == IntPtr.Zero)
                throw new Z3Exception("inc() called on null AST");
            Context.ASTDRQ.IncAndClear(Context, o);
            super.IncRef(o);
        }

        void DecRef(IntPtr o)
        {
            // Console.WriteLine("AST DecRef()");
            if (Context == null)
                throw new Z3Exception("dec() called on null context");
            if (o == IntPtr.Zero)
                throw new Z3Exception("dec() called on null AST");
            Context.ASTDRQ.Add(o);
            super.DecRef(o);
        }

        static AST Create(Context ctx, IntPtr obj)
        {
            
            

            switch ((Z3AstKind)Native.getAstKind(ctx.nCtx, obj))
            {
                case Z3AstKind.Z3FUNCDECLAST: return new FuncDecl(ctx, obj);
                case Z3AstKind.Z3QUANTIFIERAST: return new Quantifier(ctx, obj);
                case Z3AstKind.Z3SORTAST: return Sort.Create(ctx, obj);
                case Z3AstKind.Z3APPAST:
                case Z3AstKind.Z3NUMERALAST:
                case Z3AstKind.Z3VARAST: return Expr.Create(ctx, obj);
                default:
                    throw new Z3Exception("Unknown AST kind");
            }
        }
    }
