// $ANTLR 3.5.2 /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g 2017-07-12 09:08:49

package org.apache.cassandra.cql3;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.cql3.statements.CFProperties;
import org.apache.cassandra.cql3.statements.CreateTableStatement;
import org.apache.cassandra.cql3.statements.ParsedStatement;
import org.apache.cassandra.cql3.statements.PropertyDefinitions;

import java.util.List;

@SuppressWarnings("all")
public class CqlParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "B", "BOOLEAN", "C", "COMMENT", 
		"D", "DIGIT", "DURATION", "DURATION_UNIT", "E", "EXPONENT", "F", "FLOAT", 
		"G", "H", "HEX", "HEXNUMBER", "I", "IDENT", "INTEGER", "J", "K", "K_ADD", 
		"K_AGGREGATE", "K_ALL", "K_ALLOW", "K_ALTER", "K_AND", "K_APPLY", "K_AS", 
		"K_ASC", "K_ASCII", "K_AUTHORIZE", "K_BATCH", "K_BEGIN", "K_BIGINT", "K_BLOB", 
		"K_BOOLEAN", "K_BY", "K_CALLED", "K_CAST", "K_CLUSTERING", "K_COLUMNFAMILY", 
		"K_COMPACT", "K_CONTAINS", "K_COUNT", "K_COUNTER", "K_CREATE", "K_CUSTOM", 
		"K_DATE", "K_DECIMAL", "K_DEFAULT", "K_DELETE", "K_DESC", "K_DESCRIBE", 
		"K_DISTINCT", "K_DOUBLE", "K_DROP", "K_DURATION", "K_ENTRIES", "K_EXECUTE", 
		"K_EXISTS", "K_FILTERING", "K_FINALFUNC", "K_FLOAT", "K_FROM", "K_FROZEN", 
		"K_FULL", "K_FUNCTION", "K_FUNCTIONS", "K_GRANT", "K_GROUP", "K_IF", "K_IN", 
		"K_INDEX", "K_INET", "K_INFINITY", "K_INITCOND", "K_INPUT", "K_INSERT", 
		"K_INT", "K_INTO", "K_IS", "K_JSON", "K_KEY", "K_KEYS", "K_KEYSPACE", 
		"K_KEYSPACES", "K_LANGUAGE", "K_LIKE", "K_LIMIT", "K_LIST", "K_LOGIN", 
		"K_MAP", "K_MATERIALIZED", "K_MBEAN", "K_MBEANS", "K_MODIFY", "K_NAN", 
		"K_NOLOGIN", "K_NORECURSIVE", "K_NOSUPERUSER", "K_NOT", "K_NULL", "K_OF", 
		"K_ON", "K_OPTIONS", "K_OR", "K_ORDER", "K_PARTITION", "K_PASSWORD", "K_PER", 
		"K_PERMISSION", "K_PERMISSIONS", "K_PRIMARY", "K_RENAME", "K_REPLACE", 
		"K_RETURNS", "K_REVOKE", "K_ROLE", "K_ROLES", "K_SELECT", "K_SET", "K_SFUNC", 
		"K_SMALLINT", "K_STATIC", "K_STORAGE", "K_STYPE", "K_SUPERUSER", "K_TEXT", 
		"K_TIME", "K_TIMESTAMP", "K_TIMEUUID", "K_TINYINT", "K_TO", "K_TOKEN", 
		"K_TRIGGER", "K_TRUNCATE", "K_TTL", "K_TUPLE", "K_TYPE", "K_UNLOGGED", 
		"K_UNSET", "K_UPDATE", "K_USE", "K_USER", "K_USERS", "K_USING", "K_UUID", 
		"K_VALUES", "K_VARCHAR", "K_VARINT", "K_VIEW", "K_WHERE", "K_WITH", "K_WRITETIME", 
		"L", "LETTER", "M", "MULTILINE_COMMENT", "N", "O", "P", "Q", "QMARK", 
		"QUOTED_NAME", "R", "S", "STRING_LITERAL", "T", "U", "UUID", "V", "W", 
		"WS", "X", "Y", "Z", "'!='", "'('", "')'", "'+'", "'+='", "','", "'-'", 
		"'-='", "'.'", "':'", "';'", "'<'", "'<='", "'='", "'>'", "'>='", "'['", 
		"'\\*'", "']'", "'expr('", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__182=182;
	public static final int T__183=183;
	public static final int T__184=184;
	public static final int T__185=185;
	public static final int T__186=186;
	public static final int T__187=187;
	public static final int T__188=188;
	public static final int T__189=189;
	public static final int T__190=190;
	public static final int T__191=191;
	public static final int T__192=192;
	public static final int T__193=193;
	public static final int T__194=194;
	public static final int T__195=195;
	public static final int T__196=196;
	public static final int T__197=197;
	public static final int T__198=198;
	public static final int T__199=199;
	public static final int T__200=200;
	public static final int T__201=201;
	public static final int T__202=202;
	public static final int T__203=203;
	public static final int A=4;
	public static final int B=5;
	public static final int BOOLEAN=6;
	public static final int C=7;
	public static final int COMMENT=8;
	public static final int D=9;
	public static final int DIGIT=10;
	public static final int DURATION=11;
	public static final int DURATION_UNIT=12;
	public static final int E=13;
	public static final int EXPONENT=14;
	public static final int F=15;
	public static final int FLOAT=16;
	public static final int G=17;
	public static final int H=18;
	public static final int HEX=19;
	public static final int HEXNUMBER=20;
	public static final int I=21;
	public static final int IDENT=22;
	public static final int INTEGER=23;
	public static final int J=24;
	public static final int K=25;
	public static final int K_ADD=26;
	public static final int K_AGGREGATE=27;
	public static final int K_ALL=28;
	public static final int K_ALLOW=29;
	public static final int K_ALTER=30;
	public static final int K_AND=31;
	public static final int K_APPLY=32;
	public static final int K_AS=33;
	public static final int K_ASC=34;
	public static final int K_ASCII=35;
	public static final int K_AUTHORIZE=36;
	public static final int K_BATCH=37;
	public static final int K_BEGIN=38;
	public static final int K_BIGINT=39;
	public static final int K_BLOB=40;
	public static final int K_BOOLEAN=41;
	public static final int K_BY=42;
	public static final int K_CALLED=43;
	public static final int K_CAST=44;
	public static final int K_CLUSTERING=45;
	public static final int K_COLUMNFAMILY=46;
	public static final int K_COMPACT=47;
	public static final int K_CONTAINS=48;
	public static final int K_COUNT=49;
	public static final int K_COUNTER=50;
	public static final int K_CREATE=51;
	public static final int K_CUSTOM=52;
	public static final int K_DATE=53;
	public static final int K_DECIMAL=54;
	public static final int K_DEFAULT=55;
	public static final int K_DELETE=56;
	public static final int K_DESC=57;
	public static final int K_DESCRIBE=58;
	public static final int K_DISTINCT=59;
	public static final int K_DOUBLE=60;
	public static final int K_DROP=61;
	public static final int K_DURATION=62;
	public static final int K_ENTRIES=63;
	public static final int K_EXECUTE=64;
	public static final int K_EXISTS=65;
	public static final int K_FILTERING=66;
	public static final int K_FINALFUNC=67;
	public static final int K_FLOAT=68;
	public static final int K_FROM=69;
	public static final int K_FROZEN=70;
	public static final int K_FULL=71;
	public static final int K_FUNCTION=72;
	public static final int K_FUNCTIONS=73;
	public static final int K_GRANT=74;
	public static final int K_GROUP=75;
	public static final int K_IF=76;
	public static final int K_IN=77;
	public static final int K_INDEX=78;
	public static final int K_INET=79;
	public static final int K_INFINITY=80;
	public static final int K_INITCOND=81;
	public static final int K_INPUT=82;
	public static final int K_INSERT=83;
	public static final int K_INT=84;
	public static final int K_INTO=85;
	public static final int K_IS=86;
	public static final int K_JSON=87;
	public static final int K_KEY=88;
	public static final int K_KEYS=89;
	public static final int K_KEYSPACE=90;
	public static final int K_KEYSPACES=91;
	public static final int K_LANGUAGE=92;
	public static final int K_LIKE=93;
	public static final int K_LIMIT=94;
	public static final int K_LIST=95;
	public static final int K_LOGIN=96;
	public static final int K_MAP=97;
	public static final int K_MATERIALIZED=98;
	public static final int K_MBEAN=99;
	public static final int K_MBEANS=100;
	public static final int K_MODIFY=101;
	public static final int K_NAN=102;
	public static final int K_NOLOGIN=103;
	public static final int K_NORECURSIVE=104;
	public static final int K_NOSUPERUSER=105;
	public static final int K_NOT=106;
	public static final int K_NULL=107;
	public static final int K_OF=108;
	public static final int K_ON=109;
	public static final int K_OPTIONS=110;
	public static final int K_OR=111;
	public static final int K_ORDER=112;
	public static final int K_PARTITION=113;
	public static final int K_PASSWORD=114;
	public static final int K_PER=115;
	public static final int K_PERMISSION=116;
	public static final int K_PERMISSIONS=117;
	public static final int K_PRIMARY=118;
	public static final int K_RENAME=119;
	public static final int K_REPLACE=120;
	public static final int K_RETURNS=121;
	public static final int K_REVOKE=122;
	public static final int K_ROLE=123;
	public static final int K_ROLES=124;
	public static final int K_SELECT=125;
	public static final int K_SET=126;
	public static final int K_SFUNC=127;
	public static final int K_SMALLINT=128;
	public static final int K_STATIC=129;
	public static final int K_STORAGE=130;
	public static final int K_STYPE=131;
	public static final int K_SUPERUSER=132;
	public static final int K_TEXT=133;
	public static final int K_TIME=134;
	public static final int K_TIMESTAMP=135;
	public static final int K_TIMEUUID=136;
	public static final int K_TINYINT=137;
	public static final int K_TO=138;
	public static final int K_TOKEN=139;
	public static final int K_TRIGGER=140;
	public static final int K_TRUNCATE=141;
	public static final int K_TTL=142;
	public static final int K_TUPLE=143;
	public static final int K_TYPE=144;
	public static final int K_UNLOGGED=145;
	public static final int K_UNSET=146;
	public static final int K_UPDATE=147;
	public static final int K_USE=148;
	public static final int K_USER=149;
	public static final int K_USERS=150;
	public static final int K_USING=151;
	public static final int K_UUID=152;
	public static final int K_VALUES=153;
	public static final int K_VARCHAR=154;
	public static final int K_VARINT=155;
	public static final int K_VIEW=156;
	public static final int K_WHERE=157;
	public static final int K_WITH=158;
	public static final int K_WRITETIME=159;
	public static final int L=160;
	public static final int LETTER=161;
	public static final int M=162;
	public static final int MULTILINE_COMMENT=163;
	public static final int N=164;
	public static final int O=165;
	public static final int P=166;
	public static final int Q=167;
	public static final int QMARK=168;
	public static final int QUOTED_NAME=169;
	public static final int R=170;
	public static final int S=171;
	public static final int STRING_LITERAL=172;
	public static final int T=173;
	public static final int U=174;
	public static final int UUID=175;
	public static final int V=176;
	public static final int W=177;
	public static final int WS=178;
	public static final int X=179;
	public static final int Y=180;
	public static final int Z=181;

	// delegates
	public Cql_Parser gParser;
	public Parser[] getDelegates() {
		return new Parser[] {gParser};
	}

	// delegators


	public CqlParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public CqlParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		gParser = new Cql_Parser(input, state, this);
	}

	@Override public String[] getTokenNames() { return CqlParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g"; }


	    public void addErrorListener(ErrorListener listener)
	    {
	        gParser.addErrorListener(listener);
	    }

	    public void removeErrorListener(ErrorListener listener)
	    {
	        gParser.removeErrorListener(listener);
	    }

	    public void displayRecognitionError(String[] tokenNames, RecognitionException e)
	    {
	        gParser.displayRecognitionError(tokenNames, e);
	    }

	    protected void addRecognitionError(String msg)
	    {
	        gParser.addRecognitionError(msg);
	    }

	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    // Recovery methods are overridden to avoid wasting work on recovering from errors when the result will be
	    // ignored anyway.
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    @Override
	    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException
	    {
	        throw new MismatchedTokenException(ttype, input);
	    }

	    @Override
	    public void recover(IntStream input, RecognitionException re)
	    {
	        // Do nothing.
	    }



	// $ANTLR start "query"
	// /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g:137:1: query returns [ParsedStatement stmnt] : st= cqlStatement ( ';' )* EOF ;
	public final ParsedStatement query() throws RecognitionException {
		ParsedStatement stmnt = null;


		ParsedStatement st =null;

		try {
			// /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g:138:5: (st= cqlStatement ( ';' )* EOF )
			// /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g:138:7: st= cqlStatement ( ';' )* EOF
			{
			pushFollow(FOLLOW_cqlStatement_in_query77);
			st=cqlStatement();
			state._fsp--;

			// /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g:138:23: ( ';' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==192) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/minhdo/workspace2/BDP/cassandra-3/src/antlr/Cql.g:138:24: ';'
					{
					match(input,192,FOLLOW_192_in_query80); 
					}
					break;

				default :
					break loop1;
				}
			}

			match(input,EOF,FOLLOW_EOF_in_query84); 
			 stmnt = st; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return stmnt;
	}
	// $ANTLR end "query"

	// Delegated rules
	public Maps.Literal mapLiteral() throws RecognitionException { return gParser.mapLiteral(); }

	public void cfName(CFName name) throws RecognitionException { gParser.cfName(name); }

	public List<Tuples.Raw> tupleOfMarkersForTuples() throws RecognitionException { return gParser.tupleOfMarkersForTuples(); }

	public List<Term.Raw> singleColumnInValues() throws RecognitionException { return gParser.singleColumnInValues(); }

	public Term.Raw term() throws RecognitionException { return gParser.term(); }

	public Term.Raw collectionLiteral() throws RecognitionException { return gParser.collectionLiteral(); }

	public Tuples.INRaw inMarkerForTuple() throws RecognitionException { return gParser.inMarkerForTuple(); }

	public ColumnIdentifier non_type_ident() throws RecognitionException { return gParser.non_type_ident(); }

	public Term.Raw intValue() throws RecognitionException { return gParser.intValue(); }

	public Constants.Literal constant() throws RecognitionException { return gParser.constant(); }

	public String unreserved_function_keyword() throws RecognitionException { return gParser.unreserved_function_keyword(); }

	public CQL3Type native_type() throws RecognitionException { return gParser.native_type(); }

	public Tuples.Literal tupleLiteral() throws RecognitionException { return gParser.tupleLiteral(); }

	public CQL3Type.Raw tuple_type() throws RecognitionException { return gParser.tuple_type(); }

	public Cql_Parser.mbean_return mbean() throws RecognitionException { return gParser.mbean(); }

	public void cfamProperty(CFProperties props) throws RecognitionException { gParser.cfamProperty(props); }

	public CQL3Type.Raw collection_type() throws RecognitionException { return gParser.collection_type(); }

	public void properties(PropertyDefinitions props) throws RecognitionException { gParser.properties(props); }

	public String propertyValue() throws RecognitionException { return gParser.propertyValue(); }

	public String basic_unreserved_keyword() throws RecognitionException { return gParser.basic_unreserved_keyword(); }

	public Term.Raw setOrMapLiteral(Term.Raw t) throws RecognitionException { return gParser.setOrMapLiteral(t); }

	public CQL3Type.Raw comparatorType() throws RecognitionException { return gParser.comparatorType(); }

	public void cfamDefinition(CreateTableStatement.RawStatement expr) throws RecognitionException { gParser.cfamDefinition(expr); }

	public Tuples.Raw markerForTuple() throws RecognitionException { return gParser.markerForTuple(); }

	public void pkDef(CreateTableStatement.RawStatement expr) throws RecognitionException { gParser.pkDef(expr); }

	public List<Tuples.Literal> tupleOfTupleLiterals() throws RecognitionException { return gParser.tupleOfTupleLiterals(); }

	public List<ColumnDefinition.Raw> tupleOfIdentifiers() throws RecognitionException { return gParser.tupleOfIdentifiers(); }

	public String unreserved_keyword() throws RecognitionException { return gParser.unreserved_keyword(); }

	public void cfamColumns(CreateTableStatement.RawStatement expr) throws RecognitionException { gParser.cfamColumns(expr); }

	public boolean cfisStatic() throws RecognitionException { return gParser.cfisStatic(); }

	public CFName columnFamilyName() throws RecognitionException { return gParser.columnFamilyName(); }

	public Term.Raw value() throws RecognitionException { return gParser.value(); }

	public ParsedStatement cqlStatement() throws RecognitionException { return gParser.cqlStatement(); }

	public FieldIdentifier fident() throws RecognitionException { return gParser.fident(); }

	public ColumnIdentifier noncol_ident() throws RecognitionException { return gParser.noncol_ident(); }

	public ColumnDefinition.Raw cident() throws RecognitionException { return gParser.cident(); }

	public void property(PropertyDefinitions props) throws RecognitionException { gParser.property(props); }

	public void cfamOrdering(CFProperties props) throws RecognitionException { gParser.cfamOrdering(props); }

	public AbstractMarker.INRaw inMarker() throws RecognitionException { return gParser.inMarker(); }

	public CreateTableStatement.RawStatement createTableStatement() throws RecognitionException { return gParser.createTableStatement(); }

	public ColumnIdentifier ident() throws RecognitionException { return gParser.ident(); }



	public static final BitSet FOLLOW_cqlStatement_in_query77 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_192_in_query80 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_EOF_in_query84 = new BitSet(new long[]{0x0000000000000002L});
}
