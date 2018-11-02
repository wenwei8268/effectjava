//package com.wenwei.singleton;
///**
// * Created by wenweizww on 2018/8/22.
// */
//
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
///**
// * author:zhou_wenwei
// * mail:zhou_wenwei@wuxiapptec.com
// * date:2018/8/22
// * description:singleton
// */
//public class Evils<K,V> extends AbstractMap<K,V>
//        implements Map<K,V>, Cloneable, Serializable {
//
//    /**
//     * The default initial capacity - MUST be a power of two.
//     */
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//
//    /**
//     * The maximum capacity, used if a higher value is implicitly specified
//     * by either of the constructors with arguments.
//     * MUST be a power of two <= 1<<30.
//     */
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    /**
//     * The load factor used when none specified in constructor.
//     */
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    /**
//     * The bin count threshold for using a tree rather than list for a
//     * bin.  Bins are converted to trees when adding an element to a
//     * bin with at least this many nodes. The value must be greater
//     * than 2 and should be at least 8 to mesh with assumptions in
//     * tree removal about conversion back to plain bins upon
//     * shrinkage.
//     */
//    static final int TREEIFY_THRESHOLD = 8;
//
//    /**
//     * The bin count threshold for untreeifying a (split) bin during a
//     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
//     * most 6 to mesh with shrinkage detection under removal.
//     */
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//    /**
//     * The smallest table capacity for which bins may be treeified.
//     * (Otherwise the table is resized if too many nodes in a bin.)
//     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
//     * between resizing and treeification thresholds.
//     */
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    static class EvilsNode<K,V> implements Map.Entry<K,V>{
//        final int hash;
//        final K key;
//        V value;
//        EvilsNode<K,V> next;
//
//        public EvilsNode(int hash, K key, V value, EvilsNode<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        @Override
//        public String toString() {
//            return "EvilsNode{" +
//                    "key=" + key +
//                    ", value=" + value +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if(o instanceof Map.Entry){
//                Map.Entry<?,?> e = (Map.Entry)o;
//                if(Objects.equals(key,e.getKey()) && Objects.equals(value,e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(key, value);
//        }
//    }
//
//
//    static final int hash(Object key){
//        int h;
//        return (key==null) ? 0 : (h = key.hashCode()) ^(h >> 16 );
//    }
//
//    static Class<?> comparableClassFor(Object x){
//        if(x instanceof Comparable){
//            Class<?> c;Type[] ts,as;Type t;
//            ParameterizedType p;
//            if((c = x.getClass()) == String.class)
//                return c;
//            if((ts = c.getGenericInterfaces()) != null){
//                for(int i = 0; i <ts.length ;++i){
////                    if(t = ts[i])
//                }
//            }
//        }
//        return null;
//    }
//    /**
//    *@description: returns a power of two size of the given target capacity
//    *@author: wenwei
//    *@params:
//    *@returns:
//    *@date: 2018/8/25 下午4:38
//    **/
//   // tableSizeFor的功能（不考虑大于最大容量的情况）是返回大于输入参数且最近的2的整数次幂的数。
//    static final int tableSizeFor(int cap){
//        int n = cap-1;
//        n |= n>>>1;
//        n |= n>>>2;
//        n |= n>>>4;
//        n |= n>>>8;
//        n |= n>>>8;
//        n |= n>>>16;
//        return (n<0)?1:(n>MAXIMUM_CAPACITY)?MAXIMUM_CAPACITY:n+1;
//    }
//    transient EvilsNode<K,V>[] table;
//
//    /**
//     * Holds cached entrySet(). Note that AbstractMap fields are used
//     * for keySet() and values().
//     */
//    transient Set<Map.Entry<K,V>> entrySet;
//
//    /**
//     * The number of key-value mappings contained in this map.
//     */
//    transient int size;
//
//    /**
//     * The number of times this HashMap has been structurally modified
//     * Structural modifications are those that change the number of mappings in
//     * the HashMap or otherwise modify its internal structure (e.g.,
//     * rehash).  This field is used to make iterators on Collection-views of
//     * the HashMap fail-fast.  (See ConcurrentModificationException).
//     */
//    transient int modCount;
//
//    /**
//     * The next size value at which to resize (capacity * load factor).
//     *
//     * @serial
//     */
//    // (The javadoc description is true upon serialization.
//    // Additionally, if the table array has not been allocated, this
//    // field holds the initial array capacity, or zero signifying
//    // DEFAULT_INITIAL_CAPACITY.)
//    int threshold;
//
//    /**
//     * The load factor for the hash table.
//     *
//     * @serial
//     */
//    final float loadFactor;
//
//    public Evils(int initialCapacity,float loadFactor){
//        if(initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " +
//                    initialCapacity);
//        if(initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if(loadFactor <=0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//    public Evils(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    /**
//     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
//     * (16) and the default load factor (0.75).
//     */
//    public Evils() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
//    }
//
//    /**
//     * Constructs a new <tt>HashMap</tt> with the same mappings as the
//     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
//     * default load factor (0.75) and an initial capacity sufficient to
//     * hold the mappings in the specified <tt>Map</tt>.
//     *
//     * @param   m the map whose mappings are to be placed in this map
//     * @throws  NullPointerException if the specified map is null
//     */
//    public Evils(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//   final void
//           (Map<? extends K,? extends V>m,boolean evict){
//        int s = m.size();
//        if(s > 0){
//            if(table == null){
//                float ft = (float)s /loadFactor +1.0F;
//                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
//                        (int)ft : MAXIMUM_CAPACITY);
//                if(t>threshold)
//                    threshold = tableSizeFor(t);
//            }else if(s > threshold)
//                    resize();
//            for(Map.Entry<? extends K,? extends V> e : m.entrySet()){
//                K key = e.getKey();
//                V value = e.getValue();
//                putVal(hash(key),key,value,false,evict);
//
//            }
//        }
//   }
//   public int size(){
//       return  size;
//   }
//   public boolean isEmpty(){
//       return size == 0;
//   }
//   public V get(Object key){
//       EvilsNode<K,V> e;
//       return (e = getNode(hash(key),key)) == null?null:e.getValue();
//   }
//   final EvilsNode<K,V> getNode(int hash,Object key){
//       EvilsNode<K,V>[] tab;EvilsNode<K,V> first,e;int n ; K k;
//       if((tab = table) != null && (n = tab.length)>0 && (first = tab[(n-1) & hash]) != null){
//           if(first.hash == hash &&
//                   ((k = first.key) == key || (key != null && key.equals(k))))
//               return first;
//           if((e = first.next) != null){
//               if(first instanceof TreeNode)
//                   return (TreeNode<K,V> first).getTreeNode(hash,key);
//               do {
//                   if (e.hash == hash &&
//                           ((k = e.key) == key || (key != null && key.equals(k))))
//                       return e;
//               } while ((e = e.next) != null);
//           }
//
//       }
//       return null;
//
//   }
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    /**
//     * Associates the specified value with the specified key in this map.
//     * If the map previously contained a mapping for the key, the old
//     * value is replaced.
//     *
//     * @param key key with which the specified value is to be associated
//     * @param value value to be associated with the specified key
//     * @return the previous value associated with <tt>key</tt>, or
//     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
//     *         (A <tt>null</tt> return can also indicate that the map
//     *         previously associated <tt>null</tt> with <tt>key</tt>.)
//     */
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final V putVal(int hash ,K key,V value, boolean onlyIfAbsent,boolean evict){
//        Evils.EvilsNode<K,V>[] tab; Evils.EvilsNode<K,V> p; int n, i;
//        if ((tab = table) == null || (n=tab.length) == 0)
//            n = (tab = resize()).length;
//        if
//    }
//
///* ------------------------------------------------------------ */
//    // Tree bins
//
//    /**
//     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
//     * extends Node) so can be used as extension of either regular or
//     * linked node.
//     */
//    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
//        Evils.TreeNode<K,V> parent;  // red-black tree links
//        Evils.TreeNode<K,V> left;
//        Evils.TreeNode<K,V> right;
//        Evils.TreeNode<K,V> prev;    // needed to unlink next upon deletion
//        boolean red;
//        TreeNode(int hash, K key, V val, Evils.Node<K,V> next) {
//            super(hash, key, val, next);
//        }
//
//        /**
//         * Returns root of tree containing this node.
//         */
//        final HashMap.TreeNode<K,V> root() {
//            for (HashMap.TreeNode<K,V> r = this, p;;) {
//                if ((p = r.parent) == null)
//                    return r;
//                r = p;
//            }
//        }
//
//        /**
//         * Ensures that the given root is the first node of its bin.
//         */
//        static <K,V> void moveRootToFront(HashMap.Node<K,V>[] tab, HashMap.TreeNode<K,V> root) {
//            int n;
//            if (root != null && tab != null && (n = tab.length) > 0) {
//                int index = (n - 1) & root.hash;
//                HashMap.TreeNode<K,V> first = (HashMap.TreeNode<K,V>)tab[index];
//                if (root != first) {
//                    HashMap.Node<K,V> rn;
//                    tab[index] = root;
//                    HashMap.TreeNode<K,V> rp = root.prev;
//                    if ((rn = root.next) != null)
//                        ((HashMap.TreeNode<K,V>)rn).prev = rp;
//                    if (rp != null)
//                        rp.next = rn;
//                    if (first != null)
//                        first.prev = root;
//                    root.next = first;
//                    root.prev = null;
//                }
//                assert checkInvariants(root);
//            }
//        }
//
//        /**
//         * Finds the node starting at root p with the given hash and key.
//         * The kc argument caches comparableClassFor(key) upon first use
//         * comparing keys.
//         */
//        final HashMap.TreeNode<K,V> find(int h, Object k, Class<?> kc) {
//            HashMap.TreeNode<K,V> p = this;
//            do {
//                int ph, dir; K pk;
//                HashMap.TreeNode<K,V> pl = p.left, pr = p.right, q;
//                if ((ph = p.hash) > h)
//                    p = pl;
//                else if (ph < h)
//                    p = pr;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if (pl == null)
//                    p = pr;
//                else if (pr == null)
//                    p = pl;
//                else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0)
//                    p = (dir < 0) ? pl : pr;
//                else if ((q = pr.find(h, k, kc)) != null)
//                    return q;
//                else
//                    p = pl;
//            } while (p != null);
//            return null;
//        }
//
//        /**
//         * Calls find for root node.
//         */
//        final HashMap.TreeNode<K,V> getTreeNode(int h, Object k) {
//            return ((parent != null) ? root() : this).find(h, k, null);
//        }
//
//        /**
//         * Tie-breaking utility for ordering insertions when equal
//         * hashCodes and non-comparable. We don't require a total
//         * order, just a consistent insertion rule to maintain
//         * equivalence across rebalancings. Tie-breaking further than
//         * necessary simplifies testing a bit.
//         */
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null ||
//                    (d = a.getClass().getName().
//                            compareTo(b.getClass().getName())) == 0)
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            return d;
//        }
//
//        /**
//         * Forms tree of the nodes linked from this node.
//         * @return root of tree
//         */
//        final void treeify(HashMap.Node<K,V>[] tab) {
//            HashMap.TreeNode<K,V> root = null;
//            for (HashMap.TreeNode<K,V> x = this, next; x != null; x = next) {
//                next = (HashMap.TreeNode<K,V>)x.next;
//                x.left = x.right = null;
//                if (root == null) {
//                    x.parent = null;
//                    x.red = false;
//                    root = x;
//                }
//                else {
//                    K k = x.key;
//                    int h = x.hash;
//                    Class<?> kc = null;
//                    for (HashMap.TreeNode<K,V> p = root;;) {
//                        int dir, ph;
//                        K pk = p.key;
//                        if ((ph = p.hash) > h)
//                            dir = -1;
//                        else if (ph < h)
//                            dir = 1;
//                        else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0)
//                            dir = tieBreakOrder(k, pk);
//
//                        HashMap.TreeNode<K,V> xp = p;
//                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                            x.parent = xp;
//                            if (dir <= 0)
//                                xp.left = x;
//                            else
//                                xp.right = x;
//                            root = balanceInsertion(root, x);
//                            break;
//                        }
//                    }
//                }
//            }
//            moveRootToFront(tab, root);
//        }
//
//        /**
//         * Returns a list of non-TreeNodes replacing those linked from
//         * this node.
//         */
//        final HashMap.Node<K,V> untreeify(HashMap<K,V> map) {
//            HashMap.Node<K,V> hd = null, tl = null;
//            for (HashMap.Node<K,V> q = this; q != null; q = q.next) {
//                HashMap.Node<K,V> p = map.replacementNode(q, null);
//                if (tl == null)
//                    hd = p;
//                else
//                    tl.next = p;
//                tl = p;
//            }
//            return hd;
//        }
//
//        /**
//         * Tree version of putVal.
//         */
//        final HashMap.TreeNode<K,V> putTreeVal(HashMap<K,V> map, HashMap.Node<K,V>[] tab,
//                                               int h, K k, V v) {
//            Class<?> kc = null;
//            boolean searched = false;
//            HashMap.TreeNode<K,V> root = (parent != null) ? root() : this;
//            for (HashMap.TreeNode<K,V> p = root;;) {
//                int dir, ph; K pk;
//                if ((ph = p.hash) > h)
//                    dir = -1;
//                else if (ph < h)
//                    dir = 1;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if ((kc == null &&
//                        (kc = comparableClassFor(k)) == null) ||
//                        (dir = compareComparables(kc, k, pk)) == 0) {
//                    if (!searched) {
//                        HashMap.TreeNode<K,V> q, ch;
//                        searched = true;
//                        if (((ch = p.left) != null &&
//                                (q = ch.find(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.find(h, k, kc)) != null))
//                            return q;
//                    }
//                    dir = tieBreakOrder(k, pk);
//                }
//
//                HashMap.TreeNode<K,V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    HashMap.Node<K,V> xpn = xp.next;
//                    HashMap.TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
//                    if (dir <= 0)
//                        xp.left = x;
//                    else
//                        xp.right = x;
//                    xp.next = x;
//                    x.parent = x.prev = xp;
//                    if (xpn != null)
//                        ((HashMap.TreeNode<K,V>)xpn).prev = x;
//                    moveRootToFront(tab, balanceInsertion(root, x));
//                    return null;
//                }
//            }
//        }
//
//        /**
//         * Removes the given node, that must be present before this call.
//         * This is messier than typical red-black deletion code because we
//         * cannot swap the contents of an interior node with a leaf
//         * successor that is pinned by "next" pointers that are accessible
//         * independently during traversal. So instead we swap the tree
//         * linkages. If the current tree appears to have too few nodes,
//         * the bin is converted back to a plain bin. (The test triggers
//         * somewhere between 2 and 6 nodes, depending on tree structure).
//         */
//        final void removeTreeNode(HashMap<K,V> map, HashMap.Node<K,V>[] tab,
//                                  boolean movable) {
//            int n;
//            if (tab == null || (n = tab.length) == 0)
//                return;
//            int index = (n - 1) & hash;
//            HashMap.TreeNode<K,V> first = (HashMap.TreeNode<K,V>)tab[index], root = first, rl;
//            HashMap.TreeNode<K,V> succ = (HashMap.TreeNode<K,V>)next, pred = prev;
//            if (pred == null)
//                tab[index] = first = succ;
//            else
//                pred.next = succ;
//            if (succ != null)
//                succ.prev = pred;
//            if (first == null)
//                return;
//            if (root.parent != null)
//                root = root.root();
//            if (root == null || root.right == null ||
//                    (rl = root.left) == null || rl.left == null) {
//                tab[index] = first.untreeify(map);  // too small
//                return;
//            }
//            HashMap.TreeNode<K,V> p = this, pl = left, pr = right, replacement;
//            if (pl != null && pr != null) {
//                HashMap.TreeNode<K,V> s = pr, sl;
//                while ((sl = s.left) != null) // find successor
//                    s = sl;
//                boolean c = s.red; s.red = p.red; p.red = c; // swap colors
//                HashMap.TreeNode<K,V> sr = s.right;
//                HashMap.TreeNode<K,V> pp = p.parent;
//                if (s == pr) { // p was s's direct parent
//                    p.parent = s;
//                    s.right = p;
//                }
//                else {
//                    HashMap.TreeNode<K,V> sp = s.parent;
//                    if ((p.parent = sp) != null) {
//                        if (s == sp.left)
//                            sp.left = p;
//                        else
//                            sp.right = p;
//                    }
//                    if ((s.right = pr) != null)
//                        pr.parent = s;
//                }
//                p.left = null;
//                if ((p.right = sr) != null)
//                    sr.parent = p;
//                if ((s.left = pl) != null)
//                    pl.parent = s;
//                if ((s.parent = pp) == null)
//                    root = s;
//                else if (p == pp.left)
//                    pp.left = s;
//                else
//                    pp.right = s;
//                if (sr != null)
//                    replacement = sr;
//                else
//                    replacement = p;
//            }
//            else if (pl != null)
//                replacement = pl;
//            else if (pr != null)
//                replacement = pr;
//            else
//                replacement = p;
//            if (replacement != p) {
//                HashMap.TreeNode<K,V> pp = replacement.parent = p.parent;
//                if (pp == null)
//                    root = replacement;
//                else if (p == pp.left)
//                    pp.left = replacement;
//                else
//                    pp.right = replacement;
//                p.left = p.right = p.parent = null;
//            }
//
//            HashMap.TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);
//
//            if (replacement == p) {  // detach
//                HashMap.TreeNode<K,V> pp = p.parent;
//                p.parent = null;
//                if (pp != null) {
//                    if (p == pp.left)
//                        pp.left = null;
//                    else if (p == pp.right)
//                        pp.right = null;
//                }
//            }
//            if (movable)
//                moveRootToFront(tab, r);
//        }
//
//        /**
//         * Splits nodes in a tree bin into lower and upper tree bins,
//         * or untreeifies if now too small. Called only from resize;
//         * see above discussion about split bits and indices.
//         *
//         * @param map the map
//         * @param tab the table for recording bin heads
//         * @param index the index of the table being split
//         * @param bit the bit of hash to split on
//         */
//        final void split(HashMap<K,V> map, HashMap.Node<K,V>[] tab, int index, int bit) {
//            HashMap.TreeNode<K,V> b = this;
//            // Relink into lo and hi lists, preserving order
//            HashMap.TreeNode<K,V> loHead = null, loTail = null;
//            HashMap.TreeNode<K,V> hiHead = null, hiTail = null;
//            int lc = 0, hc = 0;
//            for (HashMap.TreeNode<K,V> e = b, next; e != null; e = next) {
//                next = (HashMap.TreeNode<K,V>)e.next;
//                e.next = null;
//                if ((e.hash & bit) == 0) {
//                    if ((e.prev = loTail) == null)
//                        loHead = e;
//                    else
//                        loTail.next = e;
//                    loTail = e;
//                    ++lc;
//                }
//                else {
//                    if ((e.prev = hiTail) == null)
//                        hiHead = e;
//                    else
//                        hiTail.next = e;
//                    hiTail = e;
//                    ++hc;
//                }
//            }
//
//            if (loHead != null) {
//                if (lc <= UNTREEIFY_THRESHOLD)
//                    tab[index] = loHead.untreeify(map);
//                else {
//                    tab[index] = loHead;
//                    if (hiHead != null) // (else is already treeified)
//                        loHead.treeify(tab);
//                }
//            }
//            if (hiHead != null) {
//                if (hc <= UNTREEIFY_THRESHOLD)
//                    tab[index + bit] = hiHead.untreeify(map);
//                else {
//                    tab[index + bit] = hiHead;
//                    if (loHead != null)
//                        hiHead.treeify(tab);
//                }
//            }
//        }
//
//        /* ------------------------------------------------------------ */
//        // Red-black tree methods, all adapted from CLR
//
//        static <K,V> HashMap.TreeNode<K,V> rotateLeft(HashMap.TreeNode<K,V> root,
//                                                      HashMap.TreeNode<K,V> p) {
//            HashMap.TreeNode<K,V> r, pp, rl;
//            if (p != null && (r = p.right) != null) {
//                if ((rl = p.right = r.left) != null)
//                    rl.parent = p;
//                if ((pp = r.parent = p.parent) == null)
//                    (root = r).red = false;
//                else if (pp.left == p)
//                    pp.left = r;
//                else
//                    pp.right = r;
//                r.left = p;
//                p.parent = r;
//            }
//            return root;
//        }
//
//        static <K,V> HashMap.TreeNode<K,V> rotateRight(HashMap.TreeNode<K,V> root,
//                                                       HashMap.TreeNode<K,V> p) {
//            HashMap.TreeNode<K,V> l, pp, lr;
//            if (p != null && (l = p.left) != null) {
//                if ((lr = p.left = l.right) != null)
//                    lr.parent = p;
//                if ((pp = l.parent = p.parent) == null)
//                    (root = l).red = false;
//                else if (pp.right == p)
//                    pp.right = l;
//                else
//                    pp.left = l;
//                l.right = p;
//                p.parent = l;
//            }
//            return root;
//        }
//
//        static <K,V> HashMap.TreeNode<K,V> balanceInsertion(HashMap.TreeNode<K,V> root,
//                                                            HashMap.TreeNode<K,V> x) {
//            x.red = true;
//            for (HashMap.TreeNode<K,V> xp, xpp, xppl, xppr;;) {
//                if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (!xp.red || (xpp = xp.parent) == null)
//                    return root;
//                if (xp == (xppl = xpp.left)) {
//                    if ((xppr = xpp.right) != null && xppr.red) {
//                        xppr.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.right) {
//                            root = rotateLeft(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateRight(root, xpp);
//                            }
//                        }
//                    }
//                }
//                else {
//                    if (xppl != null && xppl.red) {
//                        xppl.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.left) {
//                            root = rotateRight(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateLeft(root, xpp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        static <K,V> Evils.TreeNode<K,V> balanceDeletion(Evils.TreeNode<K,V> root,
//                                                         Evils.TreeNode<K,V> x) {
//            for (Evils.TreeNode<K,V> xp, xpl, xpr;;)  {
//                if (x == null || x == root)
//                    return root;
//                else if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (x.red) {
//                    x.red = false;
//                    return root;
//                }
//                else if ((xpl = xp.left) == x) {
//                    if ((xpr = xp.right) != null && xpr.red) {
//                        xpr.red = false;
//                        xp.red = true;
//                        root = rotateLeft(root, xp);
//                        xpr = (xp = x.parent) == null ? null : xp.right;
//                    }
//                    if (xpr == null)
//                        x = xp;
//                    else {
//                        Evils.TreeNode<K,V> sl = xpr.left, sr = xpr.right;
//                        if ((sr == null || !sr.red) &&
//                                (sl == null || !sl.red)) {
//                            xpr.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sr == null || !sr.red) {
//                                if (sl != null)
//                                    sl.red = false;
//                                xpr.red = true;
//                                root = rotateRight(root, xpr);
//                                xpr = (xp = x.parent) == null ?
//                                        null : xp.right;
//                            }
//                            if (xpr != null) {
//                                xpr.red = (xp == null) ? false : xp.red;
//                                if ((sr = xpr.right) != null)
//                                    sr.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateLeft(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//                else { // symmetric
//                    if (xpl != null && xpl.red) {
//                        xpl.red = false;
//                        xp.red = true;
//                        root = rotateRight(root, xp);
//                        xpl = (xp = x.parent) == null ? null : xp.left;
//                    }
//                    if (xpl == null)
//                        x = xp;
//                    else {
//                        Evils.TreeNode<K,V> sl = xpl.left, sr = xpl.right;
//                        if ((sl == null || !sl.red) &&
//                                (sr == null || !sr.red)) {
//                            xpl.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sl == null || !sl.red) {
//                                if (sr != null)
//                                    sr.red = false;
//                                xpl.red = true;
//                                root = rotateLeft(root, xpl);
//                                xpl = (xp = x.parent) == null ?
//                                        null : xp.left;
//                            }
//                            if (xpl != null) {
//                                xpl.red = (xp == null) ? false : xp.red;
//                                if ((sl = xpl.left) != null)
//                                    sl.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateRight(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//            }
//        }
//
//        /**
//         * Recursive invariant check
//         */
//        static <K,V> boolean checkInvariants(HashMap.TreeNode<K,V> t) {
//            HashMap.TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
//                    tb = t.prev, tn = (HashMap.TreeNode<K,V>)t.next;
//            if (tb != null && tb.next != t)
//                return false;
//            if (tn != null && tn.prev != t)
//                return false;
//            if (tp != null && t != tp.left && t != tp.right)
//                return false;
//            if (tl != null && (tl.parent != t || tl.hash > t.hash))
//                return false;
//            if (tr != null && (tr.parent != t || tr.hash < t.hash))
//                return false;
//            if (t.red && tl != null && tl.red && tr != null && tr.red)
//                return false;
//            if (tl != null && !checkInvariants(tl))
//                return false;
//            if (tr != null && !checkInvariants(tr))
//                return false;
//            return true;
//        }
//    }
//}
