package cn.slimsmart.common.thread;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskPool {

	private static final String DefaultPool = "__default_taskpool__";
	private static final Logger log = Logger.getLogger(TaskPool.class.getName());

	private static Map<String, TaskPool> namedPools = new ConcurrentHashMap<String, TaskPool>();

	public static TaskPool named(String poolName) {
		if (namedPools.containsKey(poolName)) {
			return namedPools.get(poolName);
		} else {
			namedPools.put(poolName, new TaskPool(poolName));
		}
		return namedPools.get(poolName);
	}

	private String name = null;

	private TaskPool(String name) {
		this.name = name;
	}

	private ExecutorService _pool = null;
	private LinkedHashMap<String, ExecutorService> _poollist = new LinkedHashMap<String, ExecutorService>();

	private ExecutorService getPool() {
		synchronized (this) {
			if (_pool == null) {
				setPoolSize(50);
				return _pool;
			} else {
				Thread current = Thread.currentThread();
				String id = current.toString();
				int ind = id.indexOf("-");
				String poolname = (ind > 0) ? id.substring(0, ind) : "";
				if ("".equals(poolname) || !_poollist.containsKey(poolname)) {
					return _pool;
				} else {
					int i = 0; // find next pool index
					ExecutorService target = null;
					for (String es : _poollist.keySet()) {
						if (es.equals(poolname)) {
							target = _poollist.get(es);	
							break;
						}
						i++;
					}
					if (target == null) {
						final int poolindex = i;
						ThreadFactory factory = new ThreadFactory() {
							@Override
							public Thread newThread(Runnable paramRunnable) {
								return new Thread(paramRunnable, TaskPool.this.name + poolindex + "-") {
									public String toString() {
										return TaskPool.this.name + poolindex + "-" + super.toString();
									}
								};
							}
						};
						target = new ThreadPoolExecutor(0, Math.max(0, ((ThreadPoolExecutor) _pool).getMaximumPoolSize()), 5000L, TimeUnit.MILLISECONDS,
								new LinkedBlockingQueue<Runnable>(), factory, new ThreadPoolExecutor.AbortPolicy());
						_poollist.put(TaskPool.this.name + poolindex, target);
					}
					return target;
				}
			}
		}
	}

	private TaskPool setPoolSize(int poolSize) {
		if (poolSize < 1){
			return this;
		}
		synchronized (this) {
			if (_pool == null) {
				ThreadFactory factory = new ThreadFactory() {
					@Override
					public Thread newThread(Runnable paramRunnable) {
						return new Thread(paramRunnable, TaskPool.this.name + "0-") {
							public String toString() {
								return TaskPool.this.name + "0-" + super.toString();
							}
						};
					}
				};
				_pool = new ThreadPoolExecutor(Math.max(1, poolSize), Math.max(4, poolSize), 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
						factory, new ThreadPoolExecutor.AbortPolicy());
				_poollist.put(TaskPool.this.name + "0", _pool);
				log.log(Level.FINE, "TaskPool max: " + poolSize + "   started.");
			}
		}
		return this;
	}

	private TaskPool complete(final List<Runnable> tasks, boolean async) {
		ArrayList<Future<?>> calls = new ArrayList<Future<?>>();
		for (Runnable r : tasks) {
			calls.add(this.getPool().submit(r));
		}
		if (!async) {
			try {
				long last = System.currentTimeMillis();
				for (Future<?> f : calls) {
					f.get();
				}
				log.log(Level.FINE, "The tasks completed take " + (System.currentTimeMillis() - last) + "ms.");
			} catch (ExecutionException e) {
				e.printStackTrace();
				throw new RuntimeException("TaskPool completeTask Error", e);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException("TaskPool completeTask Error", e);
			}
		}
		return this;
	}

	/****** DEFAULT POOL API ********/

	public static synchronized void setPool(int poolSize) {
		named(DefaultPool).setPoolSize(poolSize);
	}

	public static void submitTask(Runnable task) {
		named(DefaultPool).getPool().submit(task);
	}

	public static void completeSyncTask(final List<Runnable> tasks) {
		named(DefaultPool).complete(tasks, false);
	}

	public static void completeAsyncTask(final List<Runnable> tasks) {
		named(DefaultPool).complete(tasks, true);
	}
}
